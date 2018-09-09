## Paging Spliterator
A simple API to make Java 8 streams of non-streaming API simple. 

After getting used to using the Java 8 streams I found myself being frustrated coming
across APIs that have not adopted or have not been updated to use the Java 8 streams yet.
For example the 1.X of the AWS Java SDK does not support streams yet, but stream support
is suppose to be added in the 2.X of the AWS Java SDK. So this API was created to help
hide some of the boilerplate code to make something "streamable". 


#### FYI
 * Currently this API does *NOT* support the splitting of a spliterator
 * Class of Interest - [PageLookup](src/main/java/com/github/born2snipe/spliterator/paging/PageLookup.java)
 * Example Implementation [here](src/test/java/com/github/born2snipe/spliterator/paging/example/RecurseDirectoryOfFilesLookup.java)
 * Example Usage [here](src/test/java/com/github/born2snipe/spliterator/paging/example/RecurseDirectoryOfFilesLookupTest.java#L82)
 
  

