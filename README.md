# Employee

### Introduction

Build an employee directory app that shows a list of employees parsed from the provided JSON endpoint. <br />
Take care to properly handle errors returned from the endpoint (or other network errors like timeouts), and ensure you do not waste network bandwidth – load expensive resources such as large photos on-demand only. 

Photos at a given URL will never change. Once one is loaded, you do not need to reload the photo. If an employee’s photo changes, they will be given a new photo URL (eg, you can treat the URL as a globally unique ID).


All screens and views which load from the network should display proper loading, empty, and error states when content is not available. If images fail to load, displaying a placeholder is fine.


The employee list does not need to be persisted to disk. You can reload it from the network on each app launch — but no more often than that. (Android developers in particular should take care not to make redundant network calls when the phone is rotated). 

On the contrary, images should be cached on disk as to not waste device bandwidth (please write your own, or use an open source image cache module – do not rely on HTTP caching for image caching).


You should structure your code so that it's testable. There is no minimum or maximum amount of test coverage we’re looking for

### Focus Area
Architecture and testability

### Architecture
DataBinding / XML <br />
Activity/Fragment <br />
ViewModel <br />
UseCase <br />
Repository <br />
DataSource <br />
Service <br />

### Technologies
Dagger2<br />
Retrofit<br />
Coroutines<br />
Glide<br />
Mockk<br />
Kluent<br />
ConstraintLayout <br />
CardView <br />
RecyclerView <br />

### Improvement 
UI Test <br />
Local data source and db <br />
Wrapper class for Glide

### Reference
This code is heavly influenced by Google architecture-sample code <br />
https://github.com/android/architecture-samples
