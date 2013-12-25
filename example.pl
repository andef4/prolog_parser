mother(mom).
father(dad).

father(Z).

parent(X) :- father(X); mother(X).

% Or<And<And<f1, f2>, f3>, mother>
% Or<And<f1, And <f2, f3>>, mother> 
% Or<And<f1, f2, f3>, mother> 

