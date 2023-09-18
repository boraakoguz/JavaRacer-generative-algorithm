# Java Racer Generative Algorithm

A Generative Algorithm AI implementation on a 2D racing simulator "Java Racer" (https://github.com/boraakoguz/JavaRacer)

## Features
The algorithm features:
- Elitism 
- Cross-over succession depending on the success of the parents
- Mutation 
- Advanced mutation techniques to improve variation 
- A roll-back mechanism in order to prevent bad training.

Project features:
- Custom map/track design
- Save mechanism
- Live data on the screen

Live Data meanings:
- Best Fit: The most points scored by an agent of the previous generation
- Gen. No: The number of generations
- Population Size: Number of agents in the population
- Instruction Size: The length of the instruction array used by the agents. Gradually expands as training continues
- Last Increase Points: The maximum points scored by an agent at the previous Instruction array expansion. Serves as a benchmark and controlls the roll-back system, advanced mutation system and stops Instruction increase in case of failure to pass the benchmark. 

## Screen Shots
A screenshot from the training process
![screenshot](https://github.com/boraakoguz/JavaRacer-generative-algorithm/blob/main/screenshots/screenshot1.png)


![gif](https://github.com/boraakoguz/JavaRacer-generative-algorithm/blob/main/screenshots/example.gif)

A gif of the start of a half-completed training

## Creating a Track

Map creation is as simple as a 100x100 png. Using the default rgb values in paint, the color coding is as follows:
- Green: Grass
- Black: Asphalt
- Red: Bounds
- White: Finish/Start Line
- Blue: Water
- Brown: Mud
- Gray: Indicates the spawn tile and spawn direction. Must be placed next to a white (Finish/Start Line) tile. Will be drawn as asphalt in game.
If you want to create your own map, edit the track.png file in source directory with paint and use the default colors otherwise the RGB values should match with the example map.

## Example Map (can be found in source):

![map](https://github.com/boraakoguz/JavaRacer-generative-algorithm/blob/main/screenshots/map.png)

## Save System
Saving is done by writing essential data on the save.txt file in the source foulder. Simply deleting or renaming the file will open up a fresh save and create a new save.txt file on the folder.
Changing the data on the file is not advised and may cause errors.

## TODO

- Add gradual saving to reduce training time

- Add post-completion check up to improve speed on some sectors of the track


  
