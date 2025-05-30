error id: file:///C:/Users/SHP/OneDrive/Escritorio/a/Carpetas/Universidad/Septimo%20semestre/Desarrollo%20III/delivery-routing-service-softdev/src/main/java/com/softdev/delivery_routing/use_cases/service/dtos/ActualizarEstadoRequestDTO.java
file:///C:/Users/SHP/OneDrive/Escritorio/a/Carpetas/Universidad/Septimo%20semestre/Desarrollo%20III/delivery-routing-service-softdev/src/main/java/com/softdev/delivery_routing/use_cases/service/dtos/ActualizarEstadoRequestDTO.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[1,1]

error in qdox parser
file content:
```java
offset: 0
uri: file:///C:/Users/SHP/OneDrive/Escritorio/a/Carpetas/Universidad/Septimo%20semestre/Desarrollo%20III/delivery-routing-service-softdev/src/main/java/com/softdev/delivery_routing/use_cases/service/dtos/ActualizarEstadoRequestDTO.java
text:
```scala
@@
```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.mtags.MtagsIndexer.index(MtagsIndexer.scala:21)
	scala.meta.internal.mtags.MtagsIndexer.index$(MtagsIndexer.scala:20)
	scala.meta.internal.mtags.JavaMtags.index(JavaMtags.scala:39)
	scala.meta.internal.mtags.Mtags$.allToplevels(Mtags.scala:150)
	scala.meta.internal.metals.DefinitionProvider.fromMtags(DefinitionProvider.scala:359)
	scala.meta.internal.metals.DefinitionProvider.$anonfun$positionOccurrence$4(DefinitionProvider.scala:278)
	scala.Option.orElse(Option.scala:477)
	scala.meta.internal.metals.DefinitionProvider.$anonfun$positionOccurrence$1(DefinitionProvider.scala:278)
	scala.Option.flatMap(Option.scala:283)
	scala.meta.internal.metals.DefinitionProvider.positionOccurrence(DefinitionProvider.scala:270)
	scala.meta.internal.metals.MetalsLspService.$anonfun$definitionOrReferences$1(MetalsLspService.scala:1580)
	scala.Option.map(Option.scala:242)
	scala.meta.internal.metals.MetalsLspService.definitionOrReferences(MetalsLspService.scala:1576)
	scala.meta.internal.metals.MetalsLspService.$anonfun$definition$1(MetalsLspService.scala:927)
	scala.meta.internal.metals.CancelTokens$.future(CancelTokens.scala:38)
	scala.meta.internal.metals.MetalsLspService.definition(MetalsLspService.scala:926)
	scala.meta.internal.metals.WorkspaceLspService.definition(WorkspaceLspService.scala:492)
	scala.meta.metals.lsp.DelegatingScalaService.definition(DelegatingScalaService.scala:65)
	java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
	java.base/java.lang.reflect.Method.invoke(Method.java:580)
	org.eclipse.lsp4j.jsonrpc.services.GenericEndpoint.lambda$recursiveFindRpcMethods$0(GenericEndpoint.java:65)
	org.eclipse.lsp4j.jsonrpc.services.GenericEndpoint.request(GenericEndpoint.java:128)
	org.eclipse.lsp4j.jsonrpc.RemoteEndpoint.handleRequest(RemoteEndpoint.java:271)
	org.eclipse.lsp4j.jsonrpc.RemoteEndpoint.consume(RemoteEndpoint.java:201)
	org.eclipse.lsp4j.jsonrpc.json.StreamMessageProducer.handleMessage(StreamMessageProducer.java:185)
	org.eclipse.lsp4j.jsonrpc.json.StreamMessageProducer.listen(StreamMessageProducer.java:97)
	org.eclipse.lsp4j.jsonrpc.json.ConcurrentMessageProcessor.run(ConcurrentMessageProcessor.java:114)
	java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:572)
	java.base/java.util.concurrent.FutureTask.run(FutureTask.java:317)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	java.base/java.lang.Thread.run(Thread.java:1583)
```
#### Short summary: 

QDox parse error in file:///C:/Users/SHP/OneDrive/Escritorio/a/Carpetas/Universidad/Septimo%20semestre/Desarrollo%20III/delivery-routing-service-softdev/src/main/java/com/softdev/delivery_routing/use_cases/service/dtos/ActualizarEstadoRequestDTO.java