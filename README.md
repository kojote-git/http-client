## http client
This is a small library that provides an API for making HTTP requests.

For example, you can send GET request in just few lines:
``` Java
HttpRequestExecutor executor = new SyncHttpRequestExecutor();
HttpRequest request = HttpGET.create("http://path/to/file");
HttpResponse response = executor.execute(request);
```
The same is true for other kinds of requests.
