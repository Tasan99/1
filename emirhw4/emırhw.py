import csv


class Player:
    def __init__(self, name, points, rebounds, assists):
        #Fırst of all we are makıng a Player class and gıve the attrıbutes whıch we are goıng to gıve from csv fıle
        #Thıs ıs the constructor of player class
        self.name = name
        self.points = points
        self.rebounds = rebounds
        self.assists = assists

class RedBlackNode:
    def __init__(self, player):
        #Representıng our node on tree
        self.player = player  #Player storıng ın the node 
        self.color = 'RED'  # New nodes are red by default
        self.parent = None
        self.left = None
        self.right = None

class RedBlackTree:
    #Creatıng the tree
    def __init__(self):
        #I have looked from ınternet to represent the end of the tree usıng-NIL
        self.NIL = RedBlackNode(Player('', 0, 0, 0))  # Sentinel NIL node
        self.NIL.color = 'BLACK' #NIL(NIL: A sentinel node representing the end of the tree.)
        self.root = self.NIL

    def insert(self, player):
        # Create a new node for the player
        new_node = RedBlackNode(player) #Putıng the player to new node,creatıng a new node for player
        new_node.left = self.NIL #node's left and right children are initially set to the sentinel NIL node, indicating that it doesn't have any children yet.
        new_node.right = self.NIL

        y = None
        x = self.root # Start at the root of the tree

        # Traverse the tree and find the correct position for the new node
        #Lookıng correct posıtıon to ınsert the tree
        while x != self.NIL:  #ıf ıt ıs not empty
            y = x
            #Comparıng by theır names
            if new_node.player.name < x.player.name: #If the new player name less ,goes left subtree
                x = x.left
            elif new_node.player.name > x.player.name:#If ıt ıs hıgher goes rıght
                x = x.right
            else:
                # The player already exısts; update their stats ınstead
                x.player.points += player.points
                x.player.rebounds += player.rebounds
                x.player.assists += player.assists
                return  # No need to ınsert; the exısting node was updated

        # Set the parent of the new node
        new_node.parent = y
        if y is None:
            # The tree was empty; the new node is the root
            self.root = new_node
        elif new_node.player.name < y.player.name:#If the new player's name ıs less than y.player.name, the new node is ınserted as the left child of y; otherwise, ıt's ınserted as the rıght chıld.
            y.left = new_node
        else:
            y.right = new_node

        # The new node is red by default
        new_node.color = 'RED'

        # Fix the Red-Black Tree properties , after ınsertıan fıx any vıoltıons of tree propertıes that can occur
        self.fix_insert(new_node)

    def update_if_exists(self, player):
        node = self.search(player.name) #Fınd the same name as the player
        if node:
            #If exıst
            node.player.points += player.points  #Update the attrıbutes
            node.player.rebounds += player.rebounds
            node.player.assists += player.assists
        else:
            #Else ınsert the player to node
            self.insert(player)

    def search(self, name, node=None):
        if node is None: #ıf node none
            node = self.root  #Make root for node
        #If not none
        while node != self.NIL and name != node.player.name: #enters a loop that continues until it either finds the node or reaches the end of the tree
            if name < node.player.name: #If player name  hıgk-her then the name parameeter ıt wıll be left ,otherwıse rıght (Left for less names due to do red and black tree)
                node = node.left
            else:
                node = node.right
        return node if node != self.NIL else None  #If the name couldnt found
    
    def in_order_walk(self, node):
        # dısplaying the contents of the tree in a sorted manner,
        if node != self.NIL: #If node ıs not empty
            self.in_order_walk(node.left) #recursıve call but for ıt ıs for left chıld dıgs to the left subtree
            print(f"{node.player.name}: {node.player.points}, {node.player.rebounds}, {node.player.assists} ({'BLACK' if node.color == 'BLACK' else 'RED'})")
            self.in_order_walk(node.right)#recursıve call for the rıght subtree

    def find_season_leaders(self):
    # Initialize variables to keep track of the leaders
        leaders = {'points': (None, 0), 'rebounds': (None, 0), 'assists': (None, 0)} #we are goıng to prınt them at the end of the season
    
        def traverse(node): #I searched thıs method from ınternet
            if node != self.NIL: #ıf not empty
                traverse(node.left) #recursıve call for left nodes to used for traversıng the tree 
                #The traverse function performs an in-order traversal of the tree (left subtree → current node → right subtree).
                if node.player.points > leaders['points'][1]:  #Change the attrıbutes whether ıt ıs hıgher then others to get the max value
                    leaders['points'] = (node.player.name, node.player.points)
                if node.player.rebounds > leaders['rebounds'][1]:
                    leaders['rebounds'] = (node.player.name, node.player.rebounds)
                if node.player.assists > leaders['assists'][1]:
                    leaders['assists'] = (node.player.name, node.player.assists)
                traverse(node.right)
    
        traverse(self.root) #The traversal is initiated from the root of the tree.

        return leaders

    def rotate_left(self, x):#move the node to the left , make rıght chıld the parent of both node
        y = x.right
        x.right = y.left #Reassıgn rıght chıld of x
        if y.left != self.NIL:
            y.left.parent = x
        y.parent = x.parent #update parent
        if x.parent is None:
            self.root = y  #reattach the y to the root

        #x becomes chıld of y
        #y x s parent ıs updated to y
        elif x == x.parent.left:
            x.parent.left = y
        else:
            x.parent.right = y
        y.left = x
        x.parent = y

    def rotate_right(self, y): # Same thıng for the rıght sıde of the node
        x = y.left
        y.left = x.right
        if x.right != self.NIL:
            x.right.parent = y
        x.parent = y.parent
        if y.parent is None:
            self.root = x
        elif y == y.parent.right:
            y.parent.right = x
        else:
            y.parent.left = x
        x.right = y
        y.parent = x


    def fix_insert(self, k):
        while k != self.root and k.parent.color == 'RED':
            # Ensure k's parent s not the root and parents color ıs red
            if k.parent == self.root:
                break

            # Handle the case when k's parent is the left child
            if k.parent == k.parent.parent.left:
                y = k.parent.parent.right  # Uncle of k
                if y and y.color == 'RED':
                    # Case 1
                    #If the uncle y is red, recolor k's parent and uncle to black, and k's grandparent to red. Then, move k up to its grandparent and repeat the process.
                    k.parent.color = 'BLACK'
                    y.color = 'BLACK'
                    k.parent.parent.color = 'RED'
                    k = k.parent.parent
                else:
                    if k == k.parent.right:
                        # Case 2
                        #If the uncle y is black or NIL, there are two sub-cases:
#Case 2: If k is a right child, perform a left rotation on k's parent, and then k becomes its parent.
#Case 3: Recolor k's parent to black and its grandparent to red, then perform a right rotation on k's grandparent.
                        k = k.parent
                        self.rotate_left(k)
                    # Case 3
                    k.parent.color = 'BLACK'
                    k.parent.parent.color = 'RED'
                    self.rotate_right(k.parent.parent)
            else:
                # Handle the case when k's parent is the right child
                y = k.parent.parent.left  # Uncle of k
                if y and y.color == 'RED': #If the uncle y ıs red
                    k.parent.color = 'BLACK'
                    y.color = 'BLACK'
                    k.parent.parent.color = 'RED'
                    k = k.parent.parent
                else: #ıf uncle of y ıs black or nıl
                    if k == k.parent.left:
                        k = k.parent
                        self.rotate_right(k)
                    #Irrespective of the previous step, k's parent is colored black, and k's grandparent is colored red. Then, a left rotation is performed on k's grandparent.
                    k.parent.color = 'BLACK'
                    k.parent.parent.color = 'RED'
                    self.rotate_left(k.parent.parent)

            # Color the root black (restoring property 2)
            self.root.color = 'BLACK'


    def print_tree(self, node, indent="", is_last=True): #PRINT THE TREE
        if node != self.NIL:
            print(indent, end='')
            if is_last:
                print("R---- ", end='')
                new_indent = indent + "     "
            else:
                print("L---- ", end='')
                new_indent = indent + "|    "
            
            print(f'{node.player.name}({node.color})')
            self.print_tree(node.left, new_indent, False)
            self.print_tree(node.right, new_indent, True)

    def print_season_leaders(self): #PRINT THE SEASON LEADERS
        leaders = self.find_season_leaders()
        print(f"Max Points: {leaders['points'][1]} - Player Name: {leaders['points'][0]}")
        print(f"Max Assists: {leaders['assists'][1]} - Player Name: {leaders['assists'][0]}")
        print(f"Max Rebounds: {leaders['rebounds'][1]} - Player Name: {leaders['rebounds'][0]}")



def build_tree_from_csv(csv_filename): # Read the csv and buıld tree
    tree = RedBlackTree()
    current_season = None
    first_season_processed = False

    with open(csv_filename, mode='r', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            season = row['Season']
            # Create a Player object from the row data
            player = Player(
                name=row['Name'],
                points=int(row['Point']),
                rebounds=int(row['Rebound']),
                assists=int(row['Assist'])
            )
            # Use update_if_exists to either update the player's stats or insert a new node
            tree.update_if_exists(player)

            # If we are encountering a new season
            if current_season and current_season != season:
                # Print the leaders for the previous season
                tree.print_season_leaders()
                current_season = season
            elif current_season is None:
                current_season = season  # The first season

            # After processing the first season, print the tree once
            if not first_season_processed and current_season != '2016—2017':
                tree.print_tree(tree.root)
                first_season_processed = True

    # After the loop, print the leaders for the last season
    tree.print_season_leaders()

    return tree

if __name__ == "__main__":
    tree = build_tree_from_csv('euroleague.csv')
    # This will print the tree after building it from the CSV
    #tree.print_tree(tree.root)   If you want to prınt the all three
