# Register construction

This folder houses the problem of register construction, which is fundamental in shared-memory programming.

## Terminology

A register can be categorized based on 3 dimensions:
- Range: Boolean and Multi-valued.
- Participants: MRMW, MRSW, SRSW.
- Safety: Safe, Regular, Atomic.

## Problem

It's well known that all types of registers are equivalent in power and equivalent with the atomic snapshot object.

The problem of register construction asks how to reconstruct a register of type A from a register of type B.

The problem of atomic snapshot construction asks how to construct an atomic snapshot object from a read-write register.

|  Base register       | Implemented register | File      | Remarks |
|----------------------|----------------------|-----------|---------|
|                      | SRSW Boolean safe    |           |         |
| SRSW Boolean safe    | MRSW Boolean safe    |           |         |
| MRSW Boolean safe    | MRSW Boolean regular |           |         |
| MRSW Boolean regular | MRSW regular         |           |         |
| MRSW regular         | SRSW atomic          |           | I did think about the use of timestamps but quickly dismissed it. |
| SRSW atomic          | MRSW atomic          |           |         |
| MRSW atomic          | MRMW atomic          |           |         |
| MRSW atomic          | Atomic snapshot      |           |         |
