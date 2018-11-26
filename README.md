# Mx_Compiler

Naive compiler for Mx language which is defined by ourself. 

The source is a C++&java-like language. The target is RISC_V NASM.

Homework for compiler course of ACM.

For more information: https://acm.sjtu.edu.cn/wiki/Compiler_2018

Main part
- Parser
- Semantic Analysis
- Intermedian Representation
- Translator to NASM

Optimization
- Register Allocation
- Redundant Jump Elimination
- Inline Function Optimization
- Dead Code Elimination
- Constant Propagation and Folding
