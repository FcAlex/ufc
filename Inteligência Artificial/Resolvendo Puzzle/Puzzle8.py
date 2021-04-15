#!/usr/bin/env python
# coding: utf-8

# In[189]:


import math
from collections import deque
import timeit


# In[193]:


class Node:
    def __init__(self,data,level,f_score):
        self.data = data
        self.level = level
        self.f_score = f_score
        
    def generate_child(self):
        x,y = self.find(self.data,'_')
        val_list = [[x,y-1],[x,y+1],[x-1,y],[x+1,y]]
        c = 0
        children = []
        for i in val_list:
            child = self.shuffle(self.data,x,y,i[0],i[1])
            if child is not None:
                child_node = Node(child,self.level+1, 0)
                children.append(child_node)
        return children
    
    def shuffle(self,puz,x1,y1,x2,y2):
        if x2 >= 0 and x2 < len(self.data) and y2 >= 0 and y2 < len(self.data):
            temp_puz = []
            temp_puz = self.copy(puz)
            temp_puz[x1][y1], temp_puz[x2][y2] = temp_puz[x2][y2], temp_puz[x1][y1]
            return temp_puz
        else:
            return None
        
    def copy(self,root):
        temp = []
        for i in root:
            temp.append(i.copy())
        return temp 
    
    def find(self,puz,x):
        for i in range(0,len(self.data)):
            for j in range(0,len(self.data)):
                if puz[i][j] == x:
                    return i,j


# In[240]:


class Puzzle:
    def __init__(self):
        self.open = []
        self.closed = []
    
    def input(self):
        puz = []
        for i in range(0,3):
            temp = input().split(" ")
            puz.append(temp)
        return puz
    
    def f(self, start, goal, heuristic):
        return heuristic(start.data, goal)+start.level
    
    def funcs(self,initial_state,goal_state):
        g_x = initial_state.level
        h_x = self.h_manhattan_dist(initial_state.value,goal_state)
        f_x = g_x + h_x
        return f_x,g_x,h_x
    
    def map(self, node):
        return ''.join(''.join(str(e) for e in node.data))
    
    def _bfs(self, start, goal):
        
        max_search_deep = 0
        max_frontier = 0
        board_visited= set()
        Queue = deque([Node(start, 0, 0)])

        while Queue:
            node = Queue.popleft()
            board_visited.add(self.map(node))
            if node.data == goal:
                return Queue, node, max_search_deep, board_visited
             
            for child in node.generate_child():
                if self.map(child) not in board_visited:
                    Queue.append(child)
                    board_visited.add(self.map(child))
                    if child.level > max_search_deep:
                        max_search_deep = max_search_deep + 1
    
            if len(Queue) > max_frontier:
                max_frontier = len(Queue)
                
        return None, None, None, None
    
    def _print(self, state):
        print('')
        for i in state.data:
            for j in i:
                print(j,end=" ")
            print("")
        print('')
    
    def solverBFS(self):
        
        print("Estado Inicial: ")
        start = self.input()
        goal = [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '_']]
        
        start_time = timeit.default_timer()
        
        queue, goal_node, max_search_deep, board_visited = self._bfs(start, goal)
        
        stop_time = timeit.default_timer()
        time = stop_time - start_time
        
        self._print(goal_node)
        
        print("Search depth: ", goal_node.level)
        print("Max Search Deep: ", max_search_deep)
        print("Running Time", format(time, '.5f'), "s")
    
    def solverAstar(self, heuristic):
        print("Estado Inicial: ")
        start = self.input()
        goal = [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '_']]
        
        start_time = timeit.default_timer()
        
        start = Node(start,0,0)
        start.f_score = self.f(start, goal, heuristic)
        self.open.append(start)
        
        k = 0
        print('')
        while True:
            cur = self.open[0]
#             print('==============', k)
#             for i in cur.data:
#                 for j in i:
#                     print(j,end=" ")
#                 print("")
#             print('==============\n') 
            if(heuristic(cur.data,goal) == 0):
                break
                
            for it in cur.generate_child():
                it.f_score = self.f(it, goal, heuristic)
                self.open.append(it)
            self.closed.append(cur)
            del self.open[0]

            self.open.sort(key = lambda x:x.f_score,reverse=False)
            
            k += 1
        stop_time = timeit.default_timer()
        time = stop_time - start_time

        g = start.level
        h = self.f(cur, goal, heuristic)
        
        self._print(cur)
        
        print("\ng(x): ", g)
        print("h(x): ", h)
        print("f(x):", g + h)
        print("Generated Nodes: ",len(self.open))
        print("Expanded Nodes: ",len(self.closed))
        print("Running Time", format(time, '.5f') + "s")
        
            
def h(start, goal): # Número de peças fora do lugar
        temp = 0
        for i in range(0, 3):
            for j in range(0, 3):
                if start[i][j] != goal[i][j] and start[i][j] != '_':
                    temp += 1
        return temp
    
def h2(start, goal): # Distância das peças até o local objetivo
    tempArr = []
    temp = 0
    for i in range (0,3):
        for j in range (0,3):
            tempArr.append(goal[i][j])

    for i in range (0,3):
        for j in range (0,3):
            current_coor = start[i][j]
            x_coor = i
            y_coor = j
            index = tempArr.index(current_coor)
            x_goal, y_goal = index//3,index%3
            if current_coor != '0':
                temp += (math.fabs(x_goal - x_coor) + math.fabs(y_goal - y_coor))

    return temp

# Casos interessantes
# 4,1,3, _,2,6, 7,5,8
# 1,2,3, 4,5,_, 7,8,6
# 8,6,4, 2,1,3, 5,7,_


# In[241]:


puz = Puzzle()
puz.solverBFS()


# In[242]:


puz.solverAstar(h)


# In[243]:


puz.solverAstar(h2)


# In[ ]:




