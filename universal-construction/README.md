# Universal construction

This folder houses the problem of universal construction to demonstrate the universality of consensus.

Link: [The Art of Multiprocessor Programming](https://g.co/kgs/w8L3qFC)

## Read-modify-write (RMW) instruction

A read-modify-write (RMW) instruction is a concurrent object with a well-defined sequential specification. Therefore, the RMW object has well-defined quiescent states.

Consider the set $F$ of all functions from the set of quiescent states to the set of quiescent states. A RMW instruction is a concurrent object that supports a method which atomically (1) fetch the existing value of the object (2) apply a function $f \in F_0 \subseteq F$ to the current state of the object (3) return the old value.

Example: fetch-and-add, compare-and-swap, fetch-and-cons, test-and-set, etc.

## The consensus problem

A consensus object has a single method `decide()` that returns value satisfying the following conditions:
- Consistent: All threads decides the same value.
- Valid: The common decision value is some thread's input.

RMW instructions can be used to implement consensus objects. The maximum number of threads that, using only the RMW instructions and a number of read-write registers, we can solve the consensus problem, is its consensus number.

## Universal RMW instruction

In a system of $n$ threads, a RMW instruction is universal if its consensus number is greater than or equal $n$.

## The universality of consensus

The problem of universal construction asks to devise a methodology to implement any wait-free concurrent objects
that have a deterministic sequential specification using a universal read-modify-write instruction.
