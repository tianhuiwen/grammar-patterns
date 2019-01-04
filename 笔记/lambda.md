# :rainbow: lambda 表达式
 Java 8 的发布，尤其是 lambda 表达式和流 API。当你越来越多的了解它们，就能写出更干净的代码。虽然一开始并不是这样。第一次看到用
lambda 表达式写出来的 Java 代码时，对这种神秘的语法感到非常失望，认为它们把Java搞得不可读，但我错了。花了一天时间做了一些 lambda
表达式和流API示例的练习后，我开心的看到了更清晰的 Java 代码。所以不要抵触 lambda 表达式以及方法引用的神秘语法，多做几次练习，
从集合类中提取、过滤数据之后，你肯定会喜欢上它。

## 简介
Lambda 表达式是一个匿名函数。简单来说，这是一种没有声明的方法，即没有访问修饰符，返回值声明和名称。在你需要使用某个方法的地方写上它。当某个方法只使用一次，而且定义很简短，使用这种速记替代之尤其有效，
这样，你就不必在类中费力写声明与方法了。
<br>
Java 中的 Lambda 表达式通常使用 (argument) -> (body) 语法书写，例如：
```
(arg1, arg2...) -> { body }

(type1 arg1, type2 arg2...) -> { body }
```
## 表达式的结构

- 一个 Lambda 表达式可以有零个或多个参数
- 参数的类型既可以明确声明，也可以根据上下文来推断。例如：(int a)与(a)效果相同
- 所有参数需包含在圆括号内，参数之间用逗号相隔。例如：(a, b) 或 (int a, int b) 或 (String a, int b, float c)
- 空圆括号代表参数集为空。例如：() -> 42
- 当只有一个参数，且其类型可推导时，圆括号（）可省略。例如：a -> return a * a
- Lambda 表达式的主体可包含零条或多条语句
- 如果 Lambda 表达式的主体只有一条语句，花括号{}可省略。匿名函数的返回类型与该主体表达式一致
- 如果 Lambda 表达式的主体包含一条以上语句，则表达式必须包含在花括号{}中（形成代码块）。匿名函数的返回类型与代码块的返回类型一致，若没有返回则为空

表达式的一些示例
```
(int a, int b) -> {  return a + b; }

() -> System.out.println("Hello World");

(String s) -> { System.out.println(s); }

() -> 42

() -> { return 3.1415 };
```
## 使用场景

#### 1. 实现Runnable
开始使用Java 8时，首先做的就是使用lambda表达式替换匿名类，而实现Runnable接口是匿名类的最好示例。看一下Java 8之前的runnable实现方法，
需要4行代码，而使用lambda表达式只需要一行代码。我们在这里做了什么呢？那就是用() -> {}代码块替代了整个匿名类
```
// Java 8之前：
new Thread(new Runnable() {
    @Override
    public void run() {
    System.out.println("Before Java8, too much code for too little to do");
    }
}).start();

//Java 8方式：
new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
```
#### 2. 遍历列表
如果你使过几年Java，你就知道针对集合类，最常见的操作就是进行迭代，并将业务逻辑应用于各个元素，例如处理订单、交易和事件的列表。
由于 Java 是命令式语言，Java 8之前的所有循环代码都是顺序的，即可以对其元素进行并行化处理。如果你想做并行过滤，就需要自己写代码，
这并不是那么容易。通过引入 lambda 表达式和默认方法，将做什么和怎么做的问题分开了，这意味着Java集合现在知道怎样做迭代，
并可以在API层面对集合元素进行并行处理。下面的例子里，我将介绍如何在使用lambda或不使用lambda表达式的情况下迭代列表。
你可以看到列表现在有了一个 forEach()  方法，它可以迭代所有对象，并将你的lambda代码应用在其中。
```
// Java 8之前：
List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
for (String feature : features) {
    System.out.println(feature);
}

// Java 8之后：
List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
features.forEach(n -> System.out.println(n));

// 使用 Java 8 的方法引用更方便，方法引用由::双冒号操作符标示，双冒号（::）操作符是 Java 中的方法引用。当们使用一个方法的引用时，目标引用放在 :: 之前，
// 目标引用提供的方法名称放在 :: 之后，即 目标引用::方法。比如:Person::getAge,调用Person的getAge方法;
features.forEach(System.out::println);
```

#### 3. 函数式接口Predicate
除了在语言层面支持函数式编程风格，Java 8也添加了一个包，叫做 java.util.function。它包含了很多类，用来支持Java的函数式编程。
其中一个便是Predicate，使用 java.util.function.Predicate 函数式接口以及lambda表达式，可以向API方法添加逻辑，用更少的代码支持更多的动态行为。
下面是Java 8 Predicate 的例子，展示了过滤集合数据的多种常用方法。Predicate接口非常适用于做过滤。
```
public static void main(args[]){
    List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

    System.out.println("Languages which starts with J :");
    filter(languages, (str)->str.startsWith("J"));

    System.out.println("Languages which ends with a ");
    filter(languages, (str)->str.endsWith("a"));

    System.out.println("Print all languages :");
    filter(languages, (str)->true);

    System.out.println("Print no language : ");
    filter(languages, (str)->false);

    System.out.println("Print language whose length greater than 4:");
    filter(languages, (str)->str.length() > 4);
}

public static void filter(List names, Predicate condition) {
    for(String name: names)  {
        if(condition.test(name)) {
            System.out.println(name + " ");
        }
    }
}

// 更好的办法
public static void filter(List names, Predicate condition) {
    names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
        System.out.println(name + " ");
    });
}
```

#### 4. Map 和 Reduce
-  Map
本例介绍最广为人知的函数式编程概念map。它允许你将对象进行转换。例如在本例中，我们将 costBeforeTax 列表的每个元素转换成为税后的值。
我们将 x -> x*x lambda表达式传到 map() 方法，后者将其应用到流中的每一个元素。然后用 forEach() 将列表元素打印出来。使用流API的收集器类，
可以得到所有含税的开销。有 toList() 这样的方法将 map 或任何其他操作的结果合并起来。由于收集器在流上做终端操作，因此之后便不能重用流了。
你甚至可以用流API的 reduce() 方法将所有数字合成一个，下一个例子将会讲到。
```
// 不使用lambda表达式为每个订单加上12%的税
List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
for (Integer cost : costBeforeTax) {
    double price = cost + .12*cost;
    System.out.println(price);
}

// 使用lambda表达式
List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
```

- Reduce

在上个例子中，可以看到map将集合类（例如列表）元素进行转换的。还有一个 reduce() 函数可以将所有值合并成一个。
Map和Reduce操作是函数式编程的核心操作，因为其功能，reduce 又被称为折叠操作。另外，reduce 并不是一个新的操作，
你有可能已经在使用它。SQL中类似 sum()、avg() 或者 count() 的聚集函数，实际上就是 reduce 操作，因为它们接收多个值并返回一个值。
流API定义的 reduceh() 函数可以接受lambda表达式，并对所有值进行合并。IntStream这样的类有类似 average()、count()、sum()
的内建方法来做 reduce 操作，也有mapToLong()、mapToDouble() 方法来做转换。这并不会限制你，你可以用内建方法，也可以自己定义。
在这个Java 8的Map Reduce示例里，我们首先对所有价格应用 12% 的VAT，然后用 reduce() 方法计算总和。

```
/ 为每个订单加上12%的税
// 老方法：
List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
double total = 0;
for (Integer cost : costBeforeTax) {
    double price = cost + .12*cost;
    total = total + price;
}
System.out.println("Total : " + total);

// 新方法：
List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
System.out.println("Total : " + bill);
```