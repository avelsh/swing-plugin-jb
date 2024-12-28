# Swing Plugin JetBrains

Hello!

The task was: 

1) [required] Create a small program with simple Java Swing UI consisting of a simple empty window with the following features.

2) [required] While the mouse cursor is outside the window, nothing happens. When the cursor enters the window, a primitive object (let it be a meme of your choice) appears, disappears, and follows the cursor based on the following rules:

    2.1) Suppose the cursor moves from right to left and at some point enters the window from the right side. Then, at the right edge of the window, the meme, scaled down to a quarter of its original size, appears exactly in the point where the cursor crossed the window border.
  
    2.2) The meme should gradually increase to its full size as the cursor moves left. For this stage, you can ignore vertical mouse movement.
    
    2.3) The resizing should be proportional to the mouse movement. It should stop when the cursor stops, scale slowly with slow movement, and quickly with fast movement.
    
    2.4) If, during the resizing, the mouse changes its horizontal direction, the meme should start shrinking back to its minimum size (one-quarter of the original).
    
    2.5) The meme disappears as soon as the mouse leaves the window.
    
    2.6) If the meme reaches its full size due to the corresponding mouse movement, the resizing stops, and the meme simply follows the mouse within the window.
    
    2.7) The described behavior should work no matter which side the cursor enters the window from. For example, if the cursor enters from the top, the meme should appear at the top edge of the window and increase in size as the mouse moves downward. The same logic applies for other directions.

3) [optional] Wrap the program into an IntelliJ IDE plugin (see public IDE and intellij-gradle plugin documentation) - your plugin must have an action that creates the window that works as described above.

4) [optional] Think of the flexibility of your solution and its complexity for unfamiliar developers. What if we decide to change a meme to another swing panel? What if we want to change some parameters like scaling to cursor movement speed ratio, panel dimensions or anything else? What if somebody from another team looks into your code or into a particular commit? Will they be able to understand it and fix the bug quickly enough?

5) [optional, advanced] Think of the following idea: if it was not a meme dragged across the window, but a complex UI panel with some content in it, that requires a lot of time to initialise - would it be possible to render the dragged entity instantly, probably with some loading indicator, move/resize it with cursor until the panel is actually fully loaded, and then replace the indicator with the true panel smoothly? So that the user would not notice anything that breaks the UX - panel size change or blinking. If you have ideas and enough time, try implementing them. Remember: even the failed implementation attempt might be a useful experience to learn from.

----------------------------------------------------
## How to build and work with this project

1. Clone the project
2. Run plugin
3. In the opened Sandbox IDE open some project or create a new one
4. Use the shortcut key ("ctrl" + "+" + "-") to activate the plugin
5. Enjoy

-----------------------------------------------------

Loading spinner simulates loading indicator for complex UI panels.

I'll be looking forward to your feedback!

Feel free to ask in case you have a problems with the plugin :)
