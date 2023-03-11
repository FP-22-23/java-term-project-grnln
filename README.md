# Second Term Project - Programming Fundamentals (2022/23)
**Author**: Guillermo Rodríguez Narbona

**UVUS**: pmd5219

This project uses a dataset containing all Tour De France stages from the year 2000 up to 2017.

## Project structure

* **/src**: Contains the project's source code files, distributed between the following packages:
  * **fp.types**: Contains the types defined for the project.
  * **fp.types.test**: Contains tests for the types in the project.
  * **fp.common**: Contains the auxiliary types defined for the project.
  * **fp.utils**:  Contains utility classes.
* **/data**: Contains the project's dataset:
    * **stages_TDF.csv**: Contains data pertaining to all Tour de France stages (2000-2017).
    
## Dataset structure

The original dataset can be found here:

https://www.kaggle.com/datasets/ralle360/historic-tour-de-france-dataset

The modified version (used in this project) is composed of 10 columns:


|StageNo|Date|Distance|Origin|Destination|Type|WinnerName|WinnerTeam|WinnerCountry|Podium|
|-------|----|--------|------|-----------|----|----------|----------|-------------|------|
|Type: `Integer`|Type: `LocalDate`|Type: `Float`|Type: `String`|Type: `String`|Type: `Enum StageType`|Type: `String`|Type: `String`|Type: `Enum RiderCountry`|Type: `List<String>`|
|Stage number|Stage date|Distance from the origin to the destination|Location at which the stage starts|Location at which the stage ends|Stage type (**Flat**, **mountain** or **time trial** stage)|Name of the rider who won the stage|Name of the team to which the winner belongs|Nation code for the winner's country|List containing the three riders who arrived in **1st**, **2nd** and **3rd** place|

## Implemented types


### Base type
_Stage_: Record representing a Tour de France stage.

**Properties**:

- _stageNo_, of type `Integer`, private. 
- _date_, of type `LocalDate`, private. 
- _distance_, of type `Float`, private. 
- _origin_, of type `String`, private. 
- _destination_, of type `String`, private. 
- _type_, of type `StageType` (`Enum`), private. 
- _podium_, of type `List`<`String`>, private. 
- _winner_, of type `Rider` (`Record`), private. 

**Constructors**: 

- C1: Takes a parameter for every property of the Record.
- C2: Takes a `String` _origin_, a `String` _destination_ and a `StageType` _type_ and constructs a _Stage_ with a stage number of 1, a date set to January 1st 2000, a distance of 0.0f and the rest of properties set to `null`.

**Restrictions**:
 
- R1: Stage number must be greater than 0.
- R2: Distance must be greater than or equal to 0.0f.
- R3: Date must be before the current date.
- R4: The podium must contain a maximum of 3 elements.

**Equality criterion**: Two stages are equal if they took place in the same date, are of the same type and have the same winner.

**Natural order criterion**: First, the dates are compared. If a stage's date foes before another's, it precedes it. If the dates are the same, the stage numbers are used.

**Otras operaciones**:
 
-	_season_: Determines the _season_ derived property, of type `String`, which represents the sports season in which the stage took place (e.g.: "2016-2017").

-  _isTimeTrial_: Determines the _isTimeTrial_ derived property, of type `Boolean`, which indicates if the stage is a time trial.

#### Auxiliary Types
-  _StageType_: Enumerated type. Represents the types of stages. Possible values are _FLAT_, _MOUNTAIN_ and _TIME_TRIAL_.
-  _RiderCountry_: Enumerated type. Contains nation codes for the nationalities of the different riders. Possible values are _GBR, GER, SVK, FRA, ITA, COL, AUS, NED, SLO, NOR, POL, BEL, RUS, ESP, CZE, LTU, IRL, POR, SUI, USA, LUX, KAZ, DEN, RSA, UKR, AUT, EST_.
-  _Rider_: Record that represents a rider. Contains the following properties:
    -  _name_: The rider's name, of type `String`.
    - _team_: The rider's team, of type `String`.
    - _country_: The rider's country, of type `RiderCountry`.