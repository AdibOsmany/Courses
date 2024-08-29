(**
Name: Adib Osmany
Pledge: I pledge my honor that I have abided by the Stevens Honor System.
**)
type 'a gt = Node of 'a *(  'a gt ) list

let t : int gt =
  Node (33 , [ Node (12 ,[]) ; Node (77 ,[ Node (37 , [ Node (14 , [])]) ; Node (48 , []) ; Node (103 , [])]) ])


let mk_leaf : 'a -> 'a gt =
  fun n ->
  Node (n ,[])


let rec height g=
 match g with
  | Node(d,[]) -> 1
  | Node(d,n) -> 1+(List.fold_left (max) 0 (List.map height n) )

let rec size g=
  match g with
   | Node(d,[]) -> 1
   | Node(d,n) -> 1+(List.fold_left (+) 0 (List.map size n) )


let rec indices input cnt=
  match input with
  | []->[]
  | h:: t ->
    cnt:: indices t (cnt+1)

let rec paths_to_leaves_helper g cnt=
  match g with
  | Node(d,[]) -> [[cnt]]
  | Node(d,n) ->  List.map (fun p -> cnt::p) (List.concat(List.map (fun p -> paths_to_leaves_helper (List.nth n p) p) (indices n 0)) )

let paths_to_leaves g =
  match g with
  | Node(d,[]) -> []
  | Node(d,n) -> List.concat (List.map (fun p -> paths_to_leaves_helper (List.nth n p) p) (indices n 0))

let is_leaf_perfect g=
  (fun j-> List.fold_left (&&) true (List.map (fun p -> (List.hd j)=p) (j))) (List.map (List.length) (paths_to_leaves g))

let rec preorder g=
  match g with
  | Node(d,[]) -> [d]
  | Node(d,n) -> d:: (List.concat (List.map preorder n))

let rec mirror g=
  match g with
  | Node(d,[]) -> g
  | Node(d,n) -> Node(d, (List.map mirror (List.rev n)))


let rec map f g=
  match g with
  | Node(d,[]) -> Node(f d,[])
  | Node(d,n) -> Node(f d, (List.map (map f) n))

  
let rec fold f g=
  match g with
  | Node(d,[]) -> f d []
  | Node(d,n) -> f d (List.map (fold f) n)

let sum t =
  fold (fun i rs -> i + List . fold_left ( fun i j -> i+ j) 0 rs ) t

let mem t e =
  fold (fun i rs -> i =e || List . exists ( fun i -> i) rs ) t
   
let rec mirror' g=
  fold (fun d n -> Node(d, List.rev(n))) t 

let rec degree g=
  match g with
  | Node(d,[]) -> 0
  | Node(d,n ) -> (List.fold_left (max) (List.length n) (List.map degree n) ) 



 