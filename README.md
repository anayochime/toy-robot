# Toy Robot Simulator
 
This application simulates a robot on a table with a grid of  5 x 5 units. There are no obstructions, commands can be issued to the robot allowing it to roam around the table top. 

**Prerequisites:** [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), [Node.js 8+](https://nodejs.org/), and [Yarn](https://yarnpkg.com/en/docs/install). You can use npm instead of Yarn, but you'll need to translate the Yarn syntax to npm.

## Getting Started

To install this application, run the following commands:

```bash
git clone https://github.com/anayochime/toy-robot-simulator.git toy-robot-simulator
cd toy-robot-simulator
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

To compile, build and run the application with a single command, run:
```bash
./mvnw spring-boot:run -Pprod
```

To run the server individually, run:
 
```bash
./mvnw spring-boot:run
```

To run the client individually, cd into the `app` folder and run:
 
```bash
yarn && yarn start
```

## Command and Rules
The robot should be able to process the below commands, commands can be issue in any case, however the directions of NORTH, EAST, WEST and SOUTH must be uppercase

**PLACE X,Y,FACING**
- Puts the toy robot on the table in position X,Y and facing NORTH,
SOUTH, EAST or WEST.
- The origin (0,0) is the SOUTH WEST most corner.
- The first valid command to the robot is a PLACE command, after that, any
sequence of commands may be issued, in any order, including another
PLACE command.
- The application will discard all commands in the sequence until a valid
PLACE command has been executed.

**MOVE**

Moves the toy robot one unit forward in the direction it is currently facing.

**LEFT**

Will rotate the robot 90° anticlockwise without changing the position of the robot.

**RIGHT**

Rotate the robot 90° clockwise without changing the position of the robot.

**REPORT**

Outputs the X,Y and F of the robot

## Ways to interact with the simulator
**Front-end**

With the application running, open http://localhost:9000 on the browser and issue your commands.

**API**

You can use a http client to send requests to the application.

**Command line**

Enter the commands directly on the console. Outputs will be printed on the console as well

## Example Input & Output
- place 0,0,NORTH
- move
- report => Output:0, 1, NORTH
- place 0, 0, NORTH
- left
- report => Output:0, 0, WEST
- place 1,2,EAST
- move
- move
- left
- move
- report => Output:3, 3, NORTH

## Improvements
- The application can be improved to have more than one robot being moved around
- Obstacles can be added to make it more fun