Code was reasonably easy to understand but there are points for why it was harder than it should have been.

There are some reasonable abstractions at a high level: Board, Piece, Position, Color
However, they're not what you expect. For example:
- Board has no state and, instead, appears to simply be a print utility
- Pieces have no logic, but are mapped to a Rule my a TypeEnum. Why the indirection?

Did they keep the solution simple?
- Separation of Piece/Type/Rule is overkill and makes me worry at how complex something genuinely more complex than Chess could become
- Project Lombok was overkill here
- The naming makes things seem more complicated than they are (rules which are actually pieces)
- No need to include incomplete code - it distracts from what is implemented

Additional Comments / Constructive Feedback
+ Scenario impl is nice
+ binary search on columns, not necessary but a nice touch
