# DataLayer - DataStore

    Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol 
    buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.
	
### Introduction
    
	-> Jetpack Library
	-> Based on Coroutines & flows
	-> Safe and Consistent
	
#### Types of Implementation

	-> Preferences DataStore
	-> Proto DataStore

### Preferences DataStore

	-> Stores and Accesses data using (key-value) pairs
	-> Work asynchronously
	-> Runs on background thread (Dispatchers.IO)
	-> Easy & quick Migration
	-> No type Safety
	
	
### Proto DataStore

	-> Typed Object (e.g. enum, lists, custom objects)
	-> Stores data as instances of a custom data type
	-> Work asynchronously
	-> Runs on background thread (Dispatchers.IO)
	-> Easy & quick Migration
	-> Requires you to define a schema using protocol buffers for type safety.


#### Using DataStore correctly
	
	-> Never create more than one instance of DataStore for a given file in the same process.
	-> The generic type of the DataStore must be immutable.
	-> Never mix usages of SingleProcessDataStore and MultiProcessDataStore

### Preference Implementation

#### Step - 1

Create an instance as follow:
	
	private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencesDataStore")


#### Step - 2

Generate a key:
	
	private val dataKey = stringPreferencesKey("data")

#### Step - 3

Save data as:
	
	fun save(data: String) {
		CoroutineScope(Dispatchers.Main).launch {
			context.dataStore.edit {
				it[dataKey] = data
			}
		}
	}


#### Step - 4

Retrieve data as:
	
	private fun getDataPrefs(): Flow<String> = context.dataStore.data
        .catch {
            Log.e(TAG, "getDataPrefs: ", it)
            showToast(context, it)
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map {
            // No type safety.
            return@map it[dataKey] ?: "nill"
        }

We can use flow as liveData for live updates
	
	val dataPrefs = getDataPrefs().asLiveData()

To observe:

	dataPrefs.observe(this) {
		Log.d("TAG", "Result: $it")
	}
