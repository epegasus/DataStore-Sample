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
	-> No type Safety
	
	
### Proto DataStore

	-> Typed Object (e.g. enum, lists, custom objects)
	-> Stores data as instances of a custom data type
	-> Work asynchronously
	-> Runs on background thread (Dispatchers.IO)
	-> Requires you to define a schema using protocol buffers for type safety.
