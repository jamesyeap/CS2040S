# Pseudocode
INIT matchPeaSoup = false
INIT matchPanCakes = false

GET number of restaurants (1 <= n <= 10)

FOR each restaurant
		SET matchPeaSoup = false
		SET matchPanCakes = false

		GET number of menu-items (1 <= k <= 10)
		GET the name of the restaurant
		FOR each menu-item
			GET the name of the menu-item
				IF name of the menu-item exactly matches "pea soup" THEN 
					SET matchPeaSoup = true	
				ELSE IF the name of the menu-item exactly matches "pancakes"
					SET matchPanCakes = true
				ELSE 
					do nothing
			
			IF both matchPeaSoup and matchPanCakes are set to true
				PRINT the name of the restaurant 


PRINT "Anywhere is fine I guess"