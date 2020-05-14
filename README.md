# Introduction

A solution to a simple Java exercise in which we create a class to construct and manipulate polynomials and their coefficients.
Included are several testers for the polynomial class.

## Methods

*@Override public String toString()*

This implementation is our first step towards returning some kind of meaningful, human readable String representations of ​this instance of ​Polynomial​. This method is not subject to testing by the JUnit tests, so you can freely choose for yourself the exact textual representation that you would like this method to produce. Having this method will become ​immensely useful for debugging all the remaining methods that you will write inside ​Polynomial​ class!

*public Polynomial(int[] coefficients)*

The ​constructor that receives as argument the array of ​coefficients that define the polynomial. For a polynomial of degree ​n,​ the ​coefficients array contains exactly ​n + 1 elements so that the coefficient of the term of order ​k is in the element ​coefficients[k]​. For example, the polynomial 5​x3​ -7​x+42thatwillbeusedasanexampleinallofthefollowingmethodswouldberepresentedas the coefficient array ​{42, -7, 0, 5}​.
Terms missing from inside the polynomial are represented by having a zero coefficient in that position. However, the ​coefficient of the highest term of every polynomial should always be nonzero​, unless the polynomial itself is identically zero. If this constructor is given as an argument a coefficient array whose highest terms are zeroes, it should simply ignore those zero coefficients. For example, if given the coefficient array ​{-1, 2, 0, 0, 0}​, the resulting polynomial would have the degree of only one, as if that coefficient array had been ​{-1, 2} without those pesky      

*public int getDegree()*

Returns the degree of ​this polynomial, that is, the exponent of its highest order term that has a nonzero coefficient. For example, the previous polynomial has degree 3. All constant polynomials, including the zero polynomial, have a degree of zero.

*public int getCoefficient(int k)*

Returns the coefficient for the term of order ​k.​ For example, the term of order 3 of the previous polynomial equals 5, and the term of order 0 equals 42. This method should work correctly even when ​k is negative or greater than the actual degree of the polynomial, and simply return zero in such cases of nonexistent terms.

*public long evaluate(int x)*

Evaluates the polynomial using the value ​x for the unknown symbolic variable of the polynomial. For example, when called with ​x = 2 for the previous example polynomial whose coefficients are {42, -7, 0, 5}​, this method would return ​68​. Your method does not have to worry about potential integer overflows, but may assume that the final and intermediate results of this computation will always stay within the range of the primitive data type ​long​.

*public Polynomial add(Polynomial other)*

Creates and returns a new ​Polynomial object that represents the result of polynomial addition of
the two polynomials ​this and ​other​. Make sure that the coefficient of the highest term of the 102 10
result is nonzero, so that adding two polynomials 5​x​ - ​x​ + 3​x and -5​x​ + 7, each having a degree of 2​
10, produces the result -​x​ + 3x​ ​ + 7 that has a degree of only 2, instead of 10.

*public Polynomial multiply(Polynomial other)*

Creates and returns a new ​Polynomial object that represents the result of polynomial multiplication of two polynomials ​this and ​other​. You can perform this multiplication by looping through all possible pairs of terms between the two polynomials and adding their products together into the result where you combine the terms of equal degree together into a single term. (If you are going to do this properly, you can perform the multiplication according to ​Horner's rule that is both faster and more numerically stable than the naive approach.)
 You should again note that multiplication can cancel out some internal terms whose coefficients 22
were originally nonzero in both polynomials. For example, ​x​ + 3 multiplied by ​x​ - 3 gives the result x4​ - 9 whose second order term ends up with a coefficient of zero. Since the product of two nonzero integers can never be zero, the highest term can never get cancelled out this way, so you always know the degree of the result once you know the degrees of the originals.

*@Override public boolean equals(Object other)*

Returns ​true if the ​other object is also a ​Polynomial of the exact same degree as ​this​, and that the coefficients of ​this and ​other polynomials are pairwise equal. If the ​other object is anything else, this method should return ​false​.
(To save you some time, you can actually implement this method after implementing the method compareTo below, since once that method is available, the logic of equality checking will be a trivial one-liner after the ​instanceof​ check.)

*@Override public int hashCode()*

Whenever you override the ​equals method in any subclass, you should also override the ​hashCode method to ensure that two objects that are considered equal by the ​equals method will also have equal integer hash codes. This method computes and returns the ​hash code of this polynomial, used to store and find this object inside some instance of ​HashSet<Polynomial>​, or some other ​hash table​ based data structure.
You get to choose for yourself the hash function that you implement, but like all hash functions, the result should depend on the degree and all of the coefficients of your polynomial. The hash function absolutely ​must satisfy the contract that whenever ​p1.equals(p2) holds for two ​Polynomial objects, then also ​p1.hashCode() == p2.hashCode()​ holds for them.
 
Of course, since this entire problem is so common and it seems silly to force everyone to reinvent the same wheel again, these days you can use the method ​hash in the ​java.util.Objects utility class to compute a good hash value for your coefficients.

*public int compareTo(Polynomial other)*

Implements the ​ordering comparison between ​this and ​other polynomials, as required by the interface ​Comparable<Polynomial>​, allowing the instances of ​Polynomial to be ​sorted or stored inside some instance of ​TreeSet<Polynomial>​. This method returns ​+1 if ​this is greater than other​, ​-1 if ​other is greater than ​this​, and returns a ​0 if both polynomials are equal in the sense of the ​equals​ method.
A total ordering relation between polynomials can be defined by many possible different rules. We shall use an ordering rule that says that ​any polynomial of a higher degree is automatically greater than any polynomial of a lower degree​, regardless of their coefficients. For two polynomials whose degrees are equal, the result of the order comparison is determined by ​the highest-order term for which the coefficients of the polynomials differ​, so that the polynomial with a larger such coefficient is considered to be greater in this ordering. (A common bug here is to loop through the coefficients from lowest to highest, instead of looping correctly down from highest to lowest.)
Be careful to ensure that this method ignores the leading zeros of high order terms if you have them inside your polynomial coefficient array, and that ​the ordering comparison criterion is precisely the one defined in the previous paragraph​. Otherwise the automated tests will reject your code, even if your code happened to define some other perfectly legal ordering relation from the infinitely many possible ordering relations!

