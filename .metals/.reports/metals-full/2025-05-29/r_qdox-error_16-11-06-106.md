error id: file:///C:/Users/SHP/OneDrive/Escritorio/a/Carpetas/Universidad/Septimo%20semestre/Desarrollo%20III/delivery-routing-service-softdev/src/main/java/com/softdev/delivery_routing/use_cases/service/ObtenerEntregaService.java
file:///C:/Users/SHP/OneDrive/Escritorio/a/Carpetas/Universidad/Septimo%20semestre/Desarrollo%20III/delivery-routing-service-softdev/src/main/java/com/softdev/delivery_routing/use_cases/service/ObtenerEntregaService.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[1,1]

error in qdox parser
file content:
```java
offset: 0
uri: file:///C:/Users/SHP/OneDrive/Escritorio/a/Carpetas/Universidad/Septimo%20semestre/Desarrollo%20III/delivery-routing-service-softdev/src/main/java/com/softdev/delivery_routing/use_cases/service/ObtenerEntregaService.java
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
	scala.meta.internal.metals.JavaDocumentHighlightProvider.$anonfun$documentHighlight$1(JavaDocumentHighlightProvider.scala:26)
	scala.collection.immutable.List.map(List.scala:247)
	scala.meta.internal.metals.JavaDocumentHighlightProvider.documentHighlight(JavaDocumentHighlightProvider.scala:22)
	scala.meta.internal.metals.MetalsLspService.$anonfun$documentHighlights$1(MetalsLspService.scala:981)
	scala.meta.internal.metals.CancelTokens$.$anonfun$apply$2(CancelTokens.scala:26)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:687)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:467)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	java.base/java.lang.Thread.run(Thread.java:1583)
```
#### Short summary: 

QDox parse error in file:///C:/Users/SHP/OneDrive/Escritorio/a/Carpetas/Universidad/Septimo%20semestre/Desarrollo%20III/delivery-routing-service-softdev/src/main/java/com/softdev/delivery_routing/use_cases/service/ObtenerEntregaService.java