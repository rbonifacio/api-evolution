# API Evolution

This is a simple Java program that collects facts about the public API of Java Libraries. The main goal is to identify API evolution. To run this program, 
you need to specify a CSV file in which each row describes a version of an API, with the following columns:

   * the API name
   * the API column 
   * the recommended JDK version of this API
   * the full path for the JAR file of the API 
 
 Example (file `releases.csv`)     

```csv
bouncy-castle,1.6.0,jdk11,/Users/rbonifacio/Documents/workspace-java/jdk11-160.jar
bouncy-castle,1.6.0,jdk12,/Users/rbonifacio/Documents/workspace-java/jdk12-160.jar
```

## To run this program

In Eclipse, you just need to configure a new Run Configuration pointing to the `br.unb.cic.api_evolution.Main` class and set the name of the CSV file used as input. Optionally, you can also specify the output format (as a second argument to the program): either "pl" to Prolog or "csv" to CSV. Improvements are welcome. 

## Output 

The default output is a prolog file, comprising three kinds of facts: release, class, and method. Based on this prolog database, it is easy to query the elements 
of the API, and thus compute what have changed between different releases. 

```prolog
release(1, "bouncy-castle", "1.6.0", "jdk11").
class(1, 0, "Lorg/bouncycastle/math/ec/GLVMultiplier").
class(1, 1, "Lorg/bouncycastle/asn1/pkcs/SafeBag").
class(1, 2, "Lorg/bouncycastle/asn1/cmc/BodyPartList").
class(1, 3, "Lorg/bouncycastle/crypto/params/DSAValidationParameters").
class(1, 4, "Lorg/bouncycastle/jcajce/spec/GOST28147ParameterSpec").
class(1, 5, "Lorg/bouncycastle/jcajce/provider/symmetric/Threefish$AlgParams_1024").
class(1, 6, "Lorg/bouncycastle/asn1/ASN1OctetStringParser").
class(1, 7, "Lorg/bouncycastle/asn1/cmp/RevDetails").
... 
method(1, 0, "<Primordial,V>", "<init>", ["Lorg/bouncycastle/math/ec/AbstractECMultiplier"], []).
method(1, 0, "<Primordial,Ljava/lang/Class>", "getClass", ["Ljava/lang/Object"], []).
method(1, 0, "<Primordial,I>", "hashCode", ["Ljava/lang/Object"], []).
method(1, 0, "<Primordial,Z>", "equals", ["Ljava/lang/Object", "Ljava/lang/Object"], []).
method(1, 0, "<Primordial,Ljava/lang/Object>", "clone", ["Ljava/lang/Object"], ["Ljava/lang/CloneNotSupportedException"]).
method(1, 0, "<Primordial,Ljava/lang/String>", "toString", ["Ljava/lang/Object"], []).
method(1, 0, "<Primordial,V>", "notify", ["Ljava/lang/Object"], []).
method(1, 0, "<Primordial,V>", "notifyAll", ["Ljava/lang/Object"], []).
method(1, 0, "<Primordial,V>", "wait", ["Ljava/lang/Object", "J"], ["Ljava/lang/InterruptedException"]).
method(1, 0, "<Primordial,V>", "wait", ["Ljava/lang/Object", "J", "I"], ["Ljava/lang/InterruptedException"]).
method(1, 0, "<Primordial,V>", "wait", ["Ljava/lang/Object"], ["Ljava/lang/InterruptedException"]).
method(1, 0, "<Primordial,V>", "finalize", ["Ljava/lang/Object"], ["Ljava/lang/Throwable"]).
method(1, 0, "<Primordial,V>", "<init>", ["Ljava/lang/Object"], []).


``` 