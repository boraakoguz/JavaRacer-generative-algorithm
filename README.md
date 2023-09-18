# Java Racer Generative Algorithm

A Generative Algorithm AI implementation on a 2D racing simulator "Java Racer" (https://github.com/boraakoguz/JavaRacer)

## Screen Shots
A screenshot from the training process
![screenshot](https://github.com/boraakoguz/JavaRacer-generative-algorithm/blob/main/screenshots/screenshot1.png)

A gif of the start of a half-completed training
![gif](https://github.com/boraakoguz/JavaRacer-generative-algorithm/blob/main/screenshots/example.gif)

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


  
