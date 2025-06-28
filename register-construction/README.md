# Register construction

This folder houses the problem of register construction, which is fundamental in shared-memory programming.

Link: [The Art of Multiprocessor Programming](https://g.co/kgs/w8L3qFC)

Link: [Atomic Snapshots of Shared Memory](https://dl.acm.org/doi/pdf/10.1145/93385.93394)

## Terminology

A register can be categorized based on 3 dimensions:
- Range: Boolean and Multi-valued.
  - Boolean registers can only store either 0 or 1.
  - Multi-valued registers can store more than 2 values.  
- Participants: MRMW, MRSW, SRSW.
  - MRMW registers stand for Multi-reader, Multi-writer registers.
  - MRSW registers stand for Multi-reader, Single-writer registers.
  - SRSW registers stand for Single-reader, Single-writer registers. 
- Safety: Safe, Regular, Atomic. 

## Problem

It's well known that all types of registers are equivalent in power and equivalent with the atomic snapshot object.

The problem of register construction asks how to reconstruct **a register of type A** from **registers of type B** in a *wait-free* manner.

The problem of atomic snapshot construction asks how to construct **an atomic snapshot object** from **read-write registers** in a *wait-free* manner.

|  Base register       | Implemented register | File      | Remarks |
|----------------------|----------------------|-----------|---------|
|                      | SRSW Boolean safe    |           |         |
| SRSW Boolean safe    | MRSW Boolean safe    | [Link](https://github.com/Huy-DNA/multiarts/blob/main/register-construction/MrswBooleanSafeRegister.java) |         |
| MRSW Boolean safe    | MRSW Boolean regular | [Link](https://github.com/Huy-DNA/multiarts/blob/main/register-construction/MrswBooleanRegularRegister.java) |         |
| MRSW Boolean regular | MRSW regular         | [Link](https://github.com/Huy-DNA/multiarts/blob/main/register-construction/MrswRegularRegister.java) |         |
| MRSW regular         | SRSW atomic          | [Link](https://github.com/Huy-DNA/multiarts/blob/main/register-construction/SrswAtomicRegister%20(with%20unbounded%20TS).java) | Unbounded timestamp required |
| SRSW atomic          | MRSW atomic          | [Link](https://github.com/Huy-DNA/multiarts/blob/main/register-construction/MrswAtomicRegister%20(with%20unbounded%20TS).java) | Unbounded timestamp required |
| MRSW atomic          | MRMW atomic          | [Link](https://github.com/Huy-DNA/multiarts/blob/main/register-construction/MrmwAtomicRegister%20(with%20unbounded%20TS).java) | Unbounded timestamp required |
| MRSW atomic          | Atomic snapshot      | [Link](https://github.com/Huy-DNA/multiarts/blob/main/register-construction/AtomicSnapshot%20(with%20unbounded%20TS).java) | Unbounded timestamp required. See challenges #1 |

## Challenges

1. The book's implementation of atomic snapshot seems to have some problems: The number of registers = The number of threads & Each register is only modified by one thread. However, I think an atomic snaphot object should allow an arbitrary number of registers and each register should be modifiable by any threads?

   Updated: https://www.cs.yale.edu/homes/aspnes/pinewiki/AtomicSnapshots.html states that an atomic snapshot is an array of MRSW registers... so maybe the book is correct.

   Updated: What is implemented in the book and here is termed single-writer atomic snapshot objects. To quote [Atomic Snapshots of Shared Memory](https://dl.acm.org/doi/pdf/10.1145/93385.93394), "a *single-writer snapshot memory*, in which each word may be updated by only, one process, from single-writer, n-reader registers". What I had in mind was a *multi-writer snapshot memory*.

3. Unbounded timestamp required...
