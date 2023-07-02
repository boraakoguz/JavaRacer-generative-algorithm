# Java Racer

A 2D basic racing game that features easy to custimize maps, collision detection, car behaviour physics, different surface types with different behaviours and a point system.


## Screen Shots
The start of the game

![start](https://github.com/boraakoguz/JavaRacer/assets/66472581/306fd8f5-973d-454e-a650-4a1edbd6436f)

Going off bounds will reduce points

![loss](https://github.com/boraakoguz/JavaRacer/assets/66472581/76bd1441-06fb-4d53-8900-c5cf9ced2ff2)

Going off the track will reduce further points

![offtrack](https://github.com/boraakoguz/JavaRacer/assets/66472581/3454d42d-cbea-4b46-9466-a10f74b6c36d)

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

![map](https://github.com/boraakoguz/JavaRacer/assets/66472581/36166a0c-732f-43a6-bc4d-49e97a0c525f)


## TODO

- Add car upgrades and car customization

- Physics overhaul

- Add Multiplayer (May be another project)

  
