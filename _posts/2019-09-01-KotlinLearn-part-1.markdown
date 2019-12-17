---
layout: post
title: Kotlin Learn part 1
tags: [Kotlin, blog]
image: '/images/posts/20.jpg'
categories: Kotlin
---

# Kotlin
### 包的定义
在源文件顶部
```
package XXX

import XXX
```

### 程序的入口  main
###  函数
>$是调用？

### 变量
定义只读局部变量   val，只能为其赋值一次。
可重新赋值的变量  var

### 空值与null检测
当某个变量的值可以为null时，必须在声明处的类型后添加 ？来标识该引用可以为空。

### 类型检测与自动类型转换
is 运算符。检测一个表达式是否是某类型的一个实例

### for、while循环
### when表达式
相当于switch

### 区间range
in
### Colletions 集合
与in联合使用

### 基本类型
基本类型：数字、字符、布尔值、数组与字符串。


### 类
#### 使用class关键字声明类
类声明由类名、类头（指定其类型参数、主构造函数等）以及由花括号包围的类体构成。


#### 主构造函数
一个类可以有一个主构造函数`(primary constructor)`以及多个次构造函数`(secondary constructor)`。也可以没有

>主构造函数是类头的一部分。

#### 次构造函数
次构造函数 ，需加前缀constructor
如果类有主构造函数，每个次构造函数都要或直接或间接通过另一个次构造函数代理主构造函数。在同一个类中代理另一个构造函数使用 this 关键字

#### 类的属性
- var声明为可变的，val声明为不可变的。
- 要使用类实例中的属性，只需要用名称引用即可。
    person.name

```

/**
 * 类名是Invoice
 */
class Invoice {
    //  大括号内是类体构成
}

/**
 * 定义一个空类
 */
class Empty

/**
 * 类定义，同时也是构造器
 */
class Person(var name: String, var age: Int) {
    // 初始化代码段，主构造函数的参数可以在init代码块中使用
    init {
        name = "张三";
        age = 18;
        println("This is --> primary constructor, name=$name, age=$age")
    }
    // 次构造函数 ，需加前缀constructor
    // 如果类有主构造函数，每个次构造函数都要，
    // 或直接或间接通过另一个次构造函数代理主构造函数。
    // 在同一个类中代理另一个构造函数使用 this 关键字
    constructor(name: String, age: Int, sex: String) : this(name,age){
        println("This is --> secondary constructor, name=$name, age=$age,sex=$sex")
    }
}
```
### 创建类的实例
Kotlin没有new关键字，创建类实例的时候只需要像调用普通函数一样调用构造函数

```
fun main(args: Array<String>) {
    var invoice = Invoice();
    var person = Person("ll", 19, "男")
    println(person.name)
}
```

### 类的修饰符
分为类属性修饰符`classModifier` 和访问权限修饰符 `_accessModifier_`
`classModifier`: 类属性修饰符，标示类本身特性。
* abstract    // 抽象类  
* final       // 类不可继承，默认属性
* enum        // 枚举类
* open        // 类可继承，类默认是final的
* annotation  // 注解类

`accessModifier`: 访问权限修饰符
* private    // 仅在同一个文件中可见
* protected  // 同一个文件中或子类可见
* public     // 所有调用的地方都可见
* internal   // 同一个模块中可见


### 继承
>Kotlin中所有的类的父类：Any
Any有三个方法equals()、hashCode() 与 toString()。

如A需显示继承另一个类B，在类头中把要继承的类放在冒号后
```
class Person(  name: String,  age: Int) {
    // 初始化代码段，主构造函数的参数可以在init代码块中使用
    init {
        println("This is --> primary constructor, name=$name, age=$age")
    }
    // 次构造函数 ，需加前缀constructor
    // 如果类有主构造函数，每个次构造函数都要，
    // 或直接或间接通过另一个次构造函数代理主构造函数。
    // 在同一个类中代理另一个构造函数使用 this 关键字
    constructor(name: String, age: Int, sex: String) : this(name,age){
        println("This is --> secondary constructor, name=$name, age=$age,sex=$sex")
    }
}

class Student( var name: String, var age: Int, var no:String, var score:Int): Person(name,age) {

}
```

有修饰符的情况
```
open class Person(open var name: String, open var  age: Int) {
    // 初始化代码段，主构造函数的参数可以在init代码块中使用
    init {
        println("This is --> primary constructor, name=$name, age=$age")
    }
    
    constructor(name: String, age: Int, sex: String) : this(name,age){
        println("This is --> secondary constructor, name=$name, age=$age,sex=$sex")
    }
}

class Student(override var name: String, override var age: Int, var no:String, var score:Int): Person(name,age) {

}
```

#### 覆盖方法
父类方法需要修饰符 `open`
子类方法需要修饰符 `override`

#### 覆盖属性
与覆盖方法类似
父类的属性需要修饰符 `open`
子类的属性需要修饰符 `override`
var可以覆盖val，反之不行。
val属性本质上是声明了一个get方法，var属性多了一个set方法
#### 子类初始化顺序
在构造子类实例的过程中，第一步完成基类的初始化。