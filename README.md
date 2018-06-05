# FiveDayForcast
# Quick Start:
- Clone project<br>
- To substitute own API key, use Constants.java <br>
- Build and Run<br>

# External Libraries:
- RxJava <br>
- Retrofit <br>
- Dagger <br>
- Room <br>
- Picasso <br>
- Butterknife <br>
- Joda-Time-Android

# Architecture:<br>
This project uses MVP pattern. It allowed for rapid iteration and it would seamlessly support any unit or instrumented tests down the line due to the separation of concerns. The project is further organised into different modules based on functionality and their usage and classification is explained below. These sections also feature any improvements that could be done to these respective modules. 

<br>

# App:
Initialises Dagger’s parent component and Joda-time to make them available for the entire lifecycle of the app.<br>

Improvements: Implementing LeakCanary to detect memory leaks

# Dagger:<br>
Dependency Injection is done via Dagger. AppComponent serves as the parent component and exposes the Retrofit Service and database for its dependent components. All the components provided here are scoped to be a Singleton and the dependent components (implemented for each screen) have scopes restricting them to their respective modules, enabling modularity of the codebase. <br>

Improvements: Using Dagger-Android to make the activities more agnostic about their injections. This would also reduce the dependency of Activity having to provide the view to initialise the dependent module. <br>

# Db: <br>

Room is used for caching the results retrieved from the API. These results (when available) are shown during a lack of network connectivity. Due to the relatively complex nested nature of the Response model, type converters are provided to Room to serialise and de-serialise results. Maybe is used as return type to indicate an empty table since Flowable will maintain a persistent connection between the observers and the database and is not designed to signal a state where the table is empty. <br>
Improvements: A more descriptive table establishing detailed relationships between different entities. Support for the entire CRUD operations. <br>

# Home:
<br>
Currently, the only screen of the app. It uses a Recyclerview to display the results fetched from the API. Picasso is used to display the icons for respective weather conditions, offloading the memory and cache management to the library. A HomeComponent is constructed via Dagger and serves as the dependent component and is responsible for injecting presenter, view and other required components for this module. Joda-time is used to parse dates/times due its robustness and ease of use compared to the JDK date/time classes. Times are retrieved as Unix time and displayed based on the locale of the user. Butterknife is used for view injection and event handling. The presenter chooses whether to subscribe to the observable returned by API or Database based on network connectivity and manages UI state during and after the connection.  <br>
Improvements: Creating a repository, so the presenter can be agnostic about the source of data. The repository will also manage storing, caching and retrieval of data, offloading those functions off the presenter. Designing a better UI with colour changes based on weather icons (Using Palette support library). Providing options to switch between metric and imperial units. Showing more details once a card is selected.<br>

# Model:
<br>
POJOs representing the response model from the API. They use GSON and Room annotations, the latter to define the relationship of entities. <br>
Improvements: Making the data classes immutable and separating entities and DAOs<br>

# Net:
<br>
Interface for Retrofit to communicate with the API. The Retrofit instance provided by Dagger uses OkHttp’s caching mechanism and uses RxJava adapter to return the results in form of an Observable. <br>
Improvements: Implementing support for pagination and refined results based on differing parameters<br>

# General Improvements:
<br>

- Tests. Tests. Tests. Unit tests for the business logic and instrumentation tests for the UI <br>

- Support for landscape/tablet UI <br>

- Allowing users to choose a city, either by a map or manual entry or choosing from the list of locations supported by the service provider. <br>

- Pull to refresh functionality <br>

- Displaying the time of retrieval if the result is from the cache/DB <br>

- Subtle animations and transitions <br>

- More use of resources instead of hardcoding values <br>



