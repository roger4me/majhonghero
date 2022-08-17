package com.eason.majiang.object;

public enum PlayerLocation {

    EAST, NORTH, WEST, SOUTH;

    public enum Relation {
        SELF, NEXT, OPPOSITE, PREVIOUS;

        public boolean isOther() {
            return this != SELF;
        }
    }
    public Relation getRelationOf(PlayerLocation other)
    {
      int dis = other.ordinal() - this.ordinal();
      if(dis < 0)
      {
          dis += 4;
      }
      return Relation.values()[dis];
    }

    public PlayerLocation getLocationOf(Relation relation) {
        return PlayerLocation.values()[(this.ordinal() + relation.ordinal())
                % 4];
    }
}
