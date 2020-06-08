
# Assignment 8: Animation in Motion
### 1.0 - Changes for Each Assignment:
####Changes from Part 1 to Part 2:
>This assignment encompasses the model, view, and controller packages for the main EasyAnimator. The previous assignment had included only the model consisting of shapes, motions, as well as a simple animation list. Some of the changes that were included in this version of the assignment were the views and controller classes. The model itself changed since the last version. With three views needed, much of the model was abstracted to avoid repetition of code, and the use of enums were implemented to reduce repetition as well. The biggest change in the model was the use of processes which are linked hash lists used to organize the shapes and their actions. These are populated by the AnimationBuilder class that was provided in the starter code. The model was modified in its overall structure to accommodate for the rotation motion that has since been removed from the assignment.


####Changes from Part 2 to Part 3: 
>This portion of the assignment concludes the addition and testing of the controller and playback view of the overall animation. The newest addition in this portion was the addition of the 'Playback' view in its whole. Similarly to the part 2, this view can be utilized directly from the terminal command line using a view argument of 'playback', and initializes the animation using a speed of 1 unless otherwise specified. This addition utilized the JPanel functionality if IntelliJ which included the use of listeners in order to ensure the ability of the user to change certain characteristics of the animation such as speed, start and stop. This view allow allows the user to start, stop and restart the animation as well as loop the animation indefinitely. In addition to this, a feature for exporting the SVG file was also implemented. Although additional features for extra credit were attempted, I ran out of time before I was able to fully implement them. All the required features for this assignment were completed, and one of the extra credit options, namely the export SVG was also included. From a technical perspective, some changes to the code were made specifically in the controller and playback classes in order to implement this new functionality. Please see the UML diagram included in the 'resources' folder for more information on the overall holistic organization of all MVC code. All changes were made in order to ensure there was no duplication of code. Duplication detection was enabled in IntelliJ in order to ensure that no duplication occured.

### 2.0 - Class Descriptions:

#### 2.1 - Model:

##### AbstractShape.java

> The abstract shape class represents the abstracted class for chapel within the animation. One of the requirements that Clark had mentioned was the flexibility in being able to incorporate new shapes if needed. The abstract class was designed to be flexible enough to incorporate any shape. The constructor uses a shapeType, dimensions, coordinates, and a color to create a shape.


##### AnimationModel.java

> The animation model class represents the actual model of a process within the animation. A linked hash list is created to store the processes along with a list of shapes. The constructor uses the processes and shapes to create a model.

##### GeneralProcess.java

> This class represents the actual process builder that takes into account all the attributes of a shape both before and after a type of motion occurs. The constructor takes these arguments and creates a general process which only constructs under a given set of conditions for the attributes.

##### SuperGeneralProcess.java

> This class extends the general process class and implements a rotation class. The ability to rotate objects was attempted but is not yet successful. This had been part of the original assignment, but has since been removed. I will keep attempting to get this in order.

#### 2.2 - Controller

##### Controller.java

> This class is the controller class that represents the connection between the model and view. Primarily focusing on the PlayBack view, the controller allows for communication between the views and models by monitoring the changes through listeners. The listeners parse for changes within a number of fields and handle event changes accordingly.

##### Keyframe.java

> This class is the class that represents the key frames that organize the processes for objects. Keyframes are used alongside the times, coordinates, dimensions, as well as the color of all shapes. 

#### 2.3 -  View:

##### TextView.java

> This class represents the view output when it comes to text by creating a large string and outputs what the shapes are doing, at what time, and what the coordinate changes are if any.

##### SVGAnimationView.java

> This class represents the view in SVG for a full animation which ingests the lists of processes and shapes and converts the output to an SVG format.

##### PlayBackView.java

> This class is responsible for allowing the user to use the Playback view which opens an interactive GUI for the users. This class holds all the functionality using the Frame library.

##### VisualAnimationView.java

> This class is responsible for the visual view. This class creates a display for the animation, and then determines the shape of the view needed based on the changes in the animation. 


### 3.0 - How to use the program:

##### This program can be used via the terminal command line. The arguments that can be made here include the input value '-in', the view '-view', the output value '-out', as well as the speed '-speed'. The possible views are either a text based view using 'text', or a visual view using 'visual', or finally a SVG formatted file using 'svg'. In addition, there is now the option 'playback' which is now also available.

#### Text View Example:

	 java -jar Homework_10.1.jar -view text -in toh-3.txt -out toh-3-output.txt

#### Visual View Example:

	 java -jar Homework_10.1.jar -view visual -in toh-3.txt -out toh-3-output.txt -speed 20

#### SVG View Example:

	 java -jar Homework_10.1.jar -view svg -in toh-3.txt -out toh-3-output.svg -speed 20

#### PlayBack View Example:

	java -jar Homework_10.1.jar -view playback -in toh-3.txt

}