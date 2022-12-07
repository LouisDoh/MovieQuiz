Feature: get a specific actor

  Scenario Outline: Getting a specific actor from DB
    Given An actor exists with ID <actorID>
    When I request that actor's details
    Then The webpage should show the actor's "<firstName>" and "<lastName>"
    Examples:
      | actorID | firstName | lastName     |
      | 1       | PENELOPE  | GUINESS      |
      | 5       | JOHNNY    | LOLLOBRIGIDA |
      | 13      | UMA       | WOOD         |
      | 53      | MENA      | TEMPLE       |
      | 79      | MAE       | HOFFMAN      |