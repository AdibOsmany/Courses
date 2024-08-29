(**
Name: Adib Osmany
Pledge: I pledge my honor that I have abided by the Stevens Honor System.
**)
type program = int list

let square : program = [0; 2; 2; 3; 3; 4; 4; 5; 5; 1]
let letter_e : program = [0;2;2;3;3;5;5;4;3;5;4;3;3;5;5;1]

(**map function taken from example file from lectures**)
let rec map : ('a -> 'b) -> 'a list -> 'b list =
  fun f l ->
  match l with
  | [] -> []
  | h::t -> f h :: map f t

(**The opposite function is essentially a helper function to mirror_image**)
let opposite p=
  match p with
  | 2 -> 4
  | 3 -> 5
  | 4 -> 2
  | 5 -> 3
  | _ -> p

(** returns a program that draws the mirror image of the input program**)
let mirror_image : int list -> int list =
  fun l ->
    map opposite l

(**helper function to rotate_90_letter**)
let letter_rotate p=
    match p with
    | 2 -> 3
    | 3 -> 4
    | 4 -> 5 
    | 5 -> 2
    | _ -> p

(**given a program returns a new one which draws the same pictures except that
they are rotated 90 degrees**)
let rotate_90_letter : int list -> int list =
  fun l ->
    map letter_rotate l

(** given a list of programs that represent letters returns a new list in which each
program draws the same pictures except that they are rotated 90 degrees**)
let rotate_90_word : int list list -> int list list =
  fun l ->
    map rotate_90_letter l

(**returns a list with n copies of x**)
let rec repeat : int -> 'a -> 'a list =
  fun n x ->
    match n with
    | 0 -> []
    | t -> x :: repeat (t-1) x

(**helper function to pantograph**)
let pan_helper n p =
    match p with
    | 0 -> [p]
    | 1 -> [p]
    | _ -> repeat n p

(**takes a list of lists and combines them into one list**)
let rec combine p=
  match p with
  | [] -> []
  | h:: t -> h @ combine t

(**returns a program that draws the same things as p (program) only enlarged n-fold. This pantograph function uses map**)
let pantograph : int -> int list -> int list =
  fun n p ->
    combine( map (pan_helper n) p)

(**pantograph function that uses recursion instead of map**)
let rec pantograph_nm : int -> int list -> int list =
  fun n p ->
    match p with
    | [] -> []
    | h:: t ->
      if (h=1) || (h=0)
      then h:: pantograph_nm n t
      else (repeat n h) @ pantograph_nm n t

(**fold function taken from example file from lectures**)
let rec fold : ('a -> 'b -> 'b) -> 'b -> 'a list -> 'b =
  fun f a l  ->
  match l with
  | [] -> a
  | h::t -> f h (fold f a t)

(**pantograph function that implements fold**)
let pantograph_f : int -> int list -> int list =
  fun n p ->
    fold (@) [] (map (pan_helper n) p)

(**helper function to coverage**)
let cov_helper (x,y) i=
    match i with
    | 2 -> (x,(y+1))
    | 3 -> ((x+1),y)
    | 4 -> (x,(y-1))
    | 5 -> ((x-1),y)
    | _ -> (x,y)

(**given a starting coordinate and a program returns the list of coordinates that the
program visits**)
let rec coverage : int*int -> int list -> (int*int) list =
  fun s g ->
    match g with
    | [] -> [s]
    | h:: t ->
      s:: coverage (cov_helper s h) t

(**helper function to compress_to_lists**)
let rec compress_lists_helper n l =
  match l with
  | [] -> []
  | h:: t ->
    if (n=h)
    then h:: compress_lists_helper n t
    else compress_lists_helper n []

(**removes adjacent elements of 'n' from the beginning of list 'l'**)
let rec remove_adjacents n l=
  match l with
  | []->[]
  | h::t ->
    if h=n
    then remove_adjacents n t
    else l

(**compresses a program by replacing adjacent copies of the same instruction with a list**)
let rec compress_to_lists l=
  match l with
  | [] -> []
  | h:: t -> (compress_lists_helper h l):: compress_to_lists (remove_adjacents h l)

(**gets first element of positive int list**)
let first l=
    match l with
    | [] -> -1
    | h:: t -> h

(**returns the length of a list**)    
let rec length l=
  match l with
  | [] -> 0
  | h::t -> 1 + length t

(**helper function to compress**)
let rec compress_helper l=
  match l with
  | []->[]
  | h:: t ->
    (first h, length h):: compress_helper t

(**compresses a program by replacing adjacent copies of the same instruction with
a tuple (m,n) where m is the instruction and n is the number of consecutive times it
should be executed**)
let compress : int list -> (int*int) list =
  fun l ->
    compress_helper (compress_to_lists l)

(**decompresses a compressed program**)
let rec uncompress : (int*int) list -> int list =
  fun l ->
    match l with
    | [] -> []
    | h:: t -> (repeat (snd h) (fst h))@ uncompress t

(**helper function to uncompress**)
let uncompress_helper p=
  (repeat (snd p) (fst p))

(**same as uncompress but uses map instead of recursion**)
let uncompress_m : (int*int) list -> int list =
  fun l ->
    combine(map uncompress_helper l )

(**same as uncompress but uses fold**)
let uncompress_f : (int*int) list -> int list =
  fun l ->
    fold (@) [] (map uncompress_helper l)

(**helper function to optimize**)
let rec optimize_helper l=
    match l with
    | []->[]
    | h:: t ->
      if (first h)= 1 then 1:: optimize_helper t
      else if (first h)=0 then 0:: optimize_helper t
      else h@ optimize_helper t

(** optimizes a program by eliminating redundant pen up and pen down instructions.**)
let rec optimize: program -> program =
 fun l ->
  match l with
  | []-> []
  | h:: t ->
    if h=1
    then optimize t
    else optimize_helper (compress_to_lists l)
