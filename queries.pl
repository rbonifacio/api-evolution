:- module(queries, [findClassesImplementingInterface/3]).

:- use_module(releases).

apiClass(bc, "org.bouncycastle.crypto.BlockCipher"). 

findClassesImplementingInterface(Release, Interface, Classes)
    :- findall(Class, (class(Release, _, Class, _, Interfaces), member(Interface, Interfaces)), Classes).


findNewMethods(New, Old, Methods) :- findall(NewMethod, 
    (class(New, IdNewClass, Class, _, _),
     class(Old, IdOldClass, Class, _, _),
     method(New, IdNewClass, _, _, _, _, NewMethod),
     not(method(Old, IdOldClass, _, _, _, _, NewMethod))), Methods). 
    
newBlockCiphers(New, Old, Classes, Count) :-
    findClassesImplementingInterface(New, "org.bouncycastle.crypto.BlockCipher", CNew),
    findClassesImplementingInterface(Old, "org.bouncycastle.crypto.BlockCipher", COld),
    subtract(CNew, COld, Classes),
    length(Classes, Count). 

newMessageDigestAlgorithms(New, Old, Classes, Count) :-
    findClassesImplementingInterface(New, "org.bouncycastle.crypto.Digest", CNew),
    findClassesImplementingInterface(Old, "org.bouncycastle.crypto.Digest", COld),
    subtract(CNew, COld, Classes),
    length(Classes, Count). 
    
