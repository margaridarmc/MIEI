module Q50 where

--1
myEnumFromTo::Int->Int->[Int]
myEnumFromTo x y | x == y = [x]
                 | x<y = x: myEnumFromTo (x+1) y
                 | otherwise = []

--2
myEnumFromThenTo::Int->Int->Int->[Int]
myEnumFromThenTo x y z | x == z = [z]
                       | x<z = x: myEnumFromThenTo (x+y-1) y z
                       | otherwise = []

--3
(+++)::[a]->[a]->[a]
(+++) [] [] = []
(+++) [] x = x
(+++) x [] = x
(+++) (h:t) l = h: (+++) t l 

--4
(!!!)::[a]->Int->a
(!!!) (h:t) 0 = h
(!!!) (h:t) x = (!!!) t (x-1)

--5
myreverse::[a]->[a]
myreverse [] = []
myreverse l = (last l):(myreverse (init l))

--6
myTake::Int->[a]->[a]
myTake x [] = []
myTake 1 (h:t) = [h]
myTake x (h:t) = h: myTake (x-1) t

--7
mydrop::Int->[a]->[a]
mydrop x [] = []
mydrop 1 (h:t) = t
mydrop x (h:t) = mydrop (x-1) t

--8
{-myzip::[a]->[b]->[(a,b)]
myzip (x:xs) (y:ys) = (x,y):myzip xs ys
myzip _ _ = []

 --9
 myElem::Eq a=>a->[a]->Bool
 myElem _ [] = False
 myElem x (h:t) | x==h = True
                | otherwise = myElem x t
-}
--10
myreplicate::Int->a->[a]
myreplicate 0 _ = []
myreplicate x y = y:myreplicate (x-1) y

--11
myIntersperse::a->[a]->[a]
myIntersperse x [] = []
myIntersperse x [y] = [y]
myIntersperse x (h:t) = h:x:myIntersperse x t

--16
myIsPrefixOf::Eq a=>[a]->[a]->Bool
myIsPrefixOf [] [] = True
myIsPrefixOf _ [] = False
myIsPrefixOf [] _ = True
myIsPrefixOf (x:xs) (y:ys) = if x==y then myIsPrefixOf xs ys else False

--17
myIsSuffixOf::Eq a=>[a]->[a]->Bool
myIsSuffixOf [] [] = True
myIsSuffixOf [] _ = True
myIsSuffixOf _ [] = False
myIsSuffixOf (x:xs) (y:ys) | (last xs == last ys) = myIsSuffixOf (init xs) (init ys)
                           | otherwise = False

--18
myIsSubsequenceOf::Eq a=>[a]->[a]->Bool
myIsSubsequenceOf [] [] = True
myIsSubsequenceOf [] l = True
myIsSubsequenceOf l [] = False
myIsSubsequenceOf (x:xs) (y:ys) | x==y = myIsSubsequenceOf xs ys
                                | otherwise = myIsSubsequenceOf (x:xs) ys

--19
myElemIndices::Eq a=>a->[a]->[Int]
myElemIndices x [] = []
myElemIndices x (h:t) = aux x (h:t) 0

aux::Eq a=>a->[a]->Int->[Int]
aux x [] i = []
aux x (h:t) i | x==h = i: aux x t (i+1)
              | otherwise = aux x t (i+1) 

--20
mynub::Eq a=> [a]->[a]
mynub [] = []
mynub (h:t) = h:mynub(myremove h t)

myremove::Eq a=>a->[a]->[a]
myremove _ [] = []
myremove x (h:t)
  |(x==h) = myremove x t
  |otherwise = h:myremove x t

--21
mydelete :: Eq a => a -> [a] -> [a]
mydelete _ [] = []
mydelete x (h:t) = auxdelete x h t

auxdelete :: Eq a => a -> a -> [a] -> [a]
auxdelete x y [] = if x == y then [] else [y]
auxdelete x y (h:t) | x == y = (h:t)
                    | otherwise = y: auxdelete x h t

--22
(\\\) :: Eq a => [a] -> [a] -> [a]
(\\\) [] _ = []
(\\\) l [] = l
(\\\) (x:xs) (y:ys) | x == y = (\\\) xs ys
                    | otherwise = x: (\\\) xs (y:ys)

--23
union :: Eq a=> [a] -> [a] -> [a]
union [] l = l
union l [] = l
union (x:xs) (y:ys) | x == y = x: union xs ys
                    | otherwise = x : union xs (y:ys)

--24
intersect :: Eq a=> [a] -> [a] -> [a]
intersect [] _ = []
intersect l [] = l 
intersect (x:xs) (y:ys) | x == y = x: intersect xs (y:ys)
                        | otherwise = intersect xs ys

--25
myinsert :: Ord a => a -> [a] -> [a]
myinsert x [] = [x]
myinsert x (h:t) | x<h = x:h:t
                 | otherwise = h: myinsert x t

--26
myUnwords :: [String] -> String
myUnwords [] = ""
myUnwords [x] = x
myUnwords (h:t) = h ++ " " ++ myUnwords t

--27
myUnlines :: [String] -> String
myUnlines [] = "\n"
myUnlines [x] = x ++ "\n"
myUnlines (h:t) = h ++ "\n" ++ myUnlines t

--28
pMaior :: Ord a => [a] -> Int
pMaior (h:t) = pMaioraux h t 0

pMaioraux :: Ord a => a -> [a] -> Int -> Int
pMaioraux x [] i = i
pMaioraux x (h:t) i | x<h = pMaioraux h t (i+1)
                    | otherwise = pMaioraux x t i

--29
temRepetidos :: Eq a => [a] -> Bool
temRepetidos [] = True
temRepetidos (h:t) = repaux h t

repaux :: Eq a => a -> [a] -> Bool
repaux x [] = False
repaux x (h:t) | x == h = True
               | otherwise = repaux x t || repaux h t

--30
{-algarismos :: [Char] -> [Char]
algarismos [] = []
algarismos (h:t) | isDigit h = h : algarismos t
                 | otherwise = algarismos t
                 -}

--31
posImpares :: [a] -> [a]
posImpares [] = []
posImpares l = posImparesaux 0 l

posImparesaux :: Int -> [a] -> [a]
posImparesaux i [] = []
posImparesaux i (h:t) = if (mod i 2)==0
                        then posImparesaux (i+1) t
                        else h : posImparesaux (i+1) t

--32
posPares :: [a] -> [a]
posPares [] = []
posPares l = posParesaux 0 l

posParesaux :: Int -> [a] -> [a]
posParesaux i [] = []
posParesaux i (h:t) = if (mod i 2)==0
                      then h : posParesaux (i+1) t
                      else posParesaux (i+1) t

--33
isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted (h:t) = isSortedaux h t

isSortedaux :: Ord a => a -> [a] -> Bool
isSortedaux x [] = True
isSortedaux x (h:t) | x<=h = isSortedaux h t
                    | otherwise = False

--34
iSort :: Ord a => [a] -> [a]
iSort [] = []
iSort (h:t) = if isSorted (h:t)
              then (h:t)
              else myinsert h (iSort t)

--35
menor :: String -> String -> Bool
menor [] [] = False
menor [] _ = True
menor _ [] = False
menor l m = if length l < length m then True else False

--36
elemMSet :: Eq a => a -> [(a,Int)] -> Bool
elemMSet a [] = False
elemMSet a ((x,y):t) | a == x = True
                     | otherwise = elemMSet a t

--37
lengthMSet :: [(a,Int)] -> Int
lengthMSet [] = 0
lengthMSet ((x,y):t) = y + lengthMSet t

--38
convertMSet :: [(a,Int)] -> [a]
convertMSet [] = []
convertMSet ((x,y):t) = myreplicate y x ++ convertMSet t

--39
insereMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
insereMSet a [] = [(a,1)]
insereMSet a ((x,y):t) | a == x = ((x,y+1):t)
                       | otherwise = (x,y) : insereMSet a t

--40
removeMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
removeMSet _ [] = []
removeMSet a ((x,y):t) | y == 1 && a == x = t
                       | y>0 && a == x = ((x,y-1):t)
                       | otherwise = (x,y) : removeMSet a t

--41
constroiMSet :: Ord a => [a] -> [(a,Int)]
constroiMSet [] = []
constroiMSet (h:t) = constroiMSetaux h t 1

constroiMSetaux :: Ord a => a -> [a] -> Int -> [(a,Int)]
constroiMSetaux a [] i = [(a,i)]
constroiMSetaux a (h:t) i | a == h = (a,i+1) : constroiMSetaux h t (i+1)
                          | otherwise = constroiMSetaux h t i
--43
catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Just x : xs) = x : catMaybes xs
catMaybes (Nothing : xs) = catMaybes xs 

--44
data Movimento = Norte | Sul | Este | Oeste
              deriving Show

posicao :: (Int,Int) -> [Movimento] -> (Int,Int)
posicao (x,y) [] = (x,y)
posicao (x,y) (Norte : xs) = posicao (x,y+1) xs
posicao (x,y) (Sul : xs) = posicao (x,y-1) xs
posicao (x,y) (Este : xs) = posicao (x+1,y) xs
posicao (x,y) (Oeste : xs) = posicao (x-1,y) xs

--46
vertical :: [Movimento] -> Bool
vertical [] = True
vertical (Este : xs) = False
vertical (Oeste :xs) = False
vertical (Norte : xs) = vertical xs
vertical (Sul : xs) = vertical xs

--47
data Posicao = Pos Int Int
             deriving Show

maisCentral :: [Posicao] -> Posicao
maisCentral ((Pos x y) :t) = maisCentralaux (Pos x y) t

maisCentralaux :: Posicao -> [Posicao] -> Posicao
maisCentralaux (Pos x y) [] = Pos x y
maisCentralaux (Pos x y) ((Pos z w):t) | (x^2 + y^2) < (z^2 + w^2) = maisCentralaux (Pos x y) t
                                       | otherwise = maisCentralaux (Pos z w) t

--48
vizinhos :: Posicao -> [Posicao] -> [Posicao]
vizinhos _ [] = []
vizinhos (Pos x y) ((Pos z w):t) | x == z+1 || x == z-1 = (Pos z w) : vizinhos (Pos x y) t
                                 | y == w+1 || y == w-1 = (Pos z w) : vizinhos (Pos x y) t
                                 | otherwise = vizinhos (Pos x y) t

--49
mesmaOrdenada :: [Posicao] -> Bool
mesmaOrdenada [] = True
mesmaOrdenada ((Pos x y):t) = mesmaOrdenadaaux (Pos x y) t

mesmaOrdenadaaux :: Posicao -> [Posicao] -> Bool
mesmaOrdenadaaux (Pos x y) [] = True
mesmaOrdenadaaux (Pos x y) ((Pos z w):t) | y == w = mesmaOrdenadaaux (Pos x y) t
                                         | otherwise = False

--50
data Semaforo = Verde | Amarelo | Vermelho
              deriving Show

interseccaoOk :: [Semaforo] -> Bool
interseccaoOk l = auxS l <= 1
              where auxS [Vermelho] = 0
                    auxS [Verde] = 1
                    auxS [Amarelo] = 1
                    auxS (Vermelho : xs) = auxS xs
                    auxS (Verde : xs) = 1 + auxS xs 
                    auxS (Amarelo : xs) = 1 + auxS xs

repetidos :: Eq a => [a] -> Bool
repetidos [] = False
repetidos (h:t) = repetidosaux h t

repetidosaux :: Eq a => a -> [a] -> Bool
repetidosaux _ [] = False
repetidosaux x (h:t) | x == h = True
                     | otherwise = repetidosaux x t || repetidosaux h t

data Tree a = Empty | Node a (Tree a) (Tree a)
     deriving Show

somaNum :: Int -> Tree Int -> Tree Int
somaNum x Empty = Empty
somaNum x (Node a e d) = (Node (a+x) (somaNum x e) (somaNum x d))

multip :: Int -> [(Int,Int,Int)] -> [(Int,Int,Int)]
multip _ [] = []
multip x ((a,b,c):t) | (mod (a+b+c) x) == 0 = (a,b,c) : multip x t
                     | otherwise = multip x t

type TabAbrev = [(Abreviatura,Palavra)]
type Abreviatura = String
type Palavra = String

--a
daPal :: TabAbrev -> Abreviatura -> Maybe Palavra
daPal [] _ = Nothing
daPal ((abrev,pal):t) x | x == abrev = Just pal
                        | otherwise = daPal t x