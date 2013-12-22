cat(tom).
parent(Fritz, 1234).


% listen
listTest(a, [b,c,d]).
listTest2([a,b, [123, Var, [22]], ABC).


% X ist genau dann ein elternteil von Y, 
% wenn X die mutter von Y ist Oder 
% wenn X der vater von Y ist. 
elternteil(X,Y) :- mutter(X,Y); vater(X,Y). 
 
% X ist genau dann ein vorfahr von Z, 
% wenn X ein elternteil von Z ist.  
vorfahr(X,Z) :- elternteil(X,Z). 
 
% X ist genau dann ein vorfahr von Z, wenn
% X ein elternteil von Y ist Und 
% Y ein vorfahre von Z ist
vorfahr(X,Z) :- elternteil(X,Y), vorfahr(Y,Z).



