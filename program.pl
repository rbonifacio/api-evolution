/** <module> An interactive module for querying API evolution

    @author: rbonifacio
*/    

:- module(main, [main/0]).

:- use_module(releases).
:- use_module(queries). 

main :-
    write("Select one of the options:                  ")  , nl,
    write(" [1] List available releases                ")  , nl,
    write(" [2] List classes of a release              ")  , nl,
    write(" [3] List classes implementing an interface ")  , nl,
    write(" [4] New methods between releases           ")  , nl 
    read(Option),
    processOption(Option). 

processOption(1) :-
    findall((Id, API, Release), release(Id, API, Release, _), Releases),
    printReleases(Releases).

processOption(2) :-
    write("Id of the release: "),
    read(Id), 
    findall((Class, SuperClass, Interface), class(Id, _, Class, SuperClass, Interface), Classes),
    printClasses(Classes).

processOption(3) :-
    write("Id of the release: "),nl,
    read(Id),  
    write("Full qualified name of the interface: "), nl,
    read(Interface), 
    findClassesImplementingInterface(Id, Interface, Classes),
    length(Classes, Count), nl,
    sort(Classes, Sorted), 
    write("Total o Classes: "), write(Count), nl,
    printClasses(Sorted).

%% processOption(4) :-
%%     write("Id of the new release: "), nl,
%%     read(IdNew),
%%     write("Id of the old release: "), nl,
%%     read(IdOld),
%%     findAll((Class, Method), class(IdNew, Res),
%%     write(Res).

processOption(_) :- write("Error"). 

%%%%%

printReleases([]).
printReleases([(Id, API, Release) | Releases]) :-
		  write(Id), write(","), write(API), write(","), write(Release), nl,
		  printReleases(Releases).

printClasses([]).

printClasses([(Class, SuperClass, Interfaces) | Classes]) :-
    write(Class), write(","), write(SuperClass), write(","), write(Interfaces), nl,
    printClasses(Classes).

printClasses([ClassName | Classes]) :- write(ClassName), nl, printClasses(Classes). 
