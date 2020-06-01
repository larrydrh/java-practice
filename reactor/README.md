## reactive总结
* 四个最重要的接口: publisher, subscribe,  subscription, processor
* 与reactive 编程对应的是传统的imperative programming.
* 传的springmvc+servelet的方式是基于线程来实现的，其本质是阻塞IO，　基于此，有一些改进的方案，如deferedresult,  SseEmitter 等, 但是在其未return时扔有可能会block

* spring reactive 的前辈　rxjava
* reactive = iterator+observer　　functional program
* reactive streams是一个标准，　有众多的实现者，　包括RxJava project reactor等
* backpressure是通过pull-push数据交换模型解决的．
* 异常处: https://stackoverflow.com/questions/53595420/correct-way-of-throwing-exceptions-with-reactor
* 只有subscribOn和publishOn schedules才能触发真的并发
* processor能一个cold stream变为一个hot stream