# threaded-is-prime
A multi-threaded java application that determines whether a number is prime.

# Approach
Given a number `n` and the number of threads `t`, the values 2 to `sqrt(n)` are divided evenly into `t` parts.
These parts are processed by multiple threads to check if the number `n` is a prime number.

# Installation
Please note that this is an eclipse project. To import it on Eclipse:

1. Clone this repository by typing `git clone https://github.com/darrensapalo/threaded-is-prime.git` on the terminal.
2. Open Eclipse.
3. Go to File > Import > General .. Existing Projects into Workspace > Select root directory .. browse.
4. Select the directory where you cloned the repository. Click finish.
5. Go to the class named `PrimeChecker` and configure the values as you need them.

# Configuration

You can allow the user to either input a number via the system input stream, or you can automatically use a pre-selected prime number.
To change this behavior, change this line:

```Java
/**
  * Determines whether the user will be asked for an input value.
  */
public static final boolean willGetUserInput = false;
	
/**
  * if <i>willGetUserInput</i> is false, then it will use this value for processing instead.
  */
public static final String defaultValue = Large;
```

## Preselected prime numbers
Conveniently, we provide a small list of prime numbers you can use. To change which default value to use, modify the assignment to the `defaultValue` static attribute.

```Java
public static final String Small   = "472882049";
public static final String Medium  = "27704267971";
public static final String Large   = "32416190071";
public static final String XLarge  = "48112959837082048697";
public static final String XXLarge = "2074722246773485207821695222107608587480996474721117292752992589912196684750549658310084416732550077";
```

