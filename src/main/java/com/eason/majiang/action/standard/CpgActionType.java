package com.eason.majiang.action.standard;

import com.eason.majiang.game.GameContext;
import com.eason.majiang.game.GameContextPlayerView;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;
import com.eason.majiang.object.TileGroupType;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CpgActionType extends AbstractPlayerActionType{
    private static final Logger logger = Logger
            .getLogger(CpgActionType.class.getSimpleName());
    private TileGroupType groupType;
    private Collection<PlayerLocation.Relation> lastActionRelations;

    protected CpgActionType(TileGroupType groupType,
                            Collection<PlayerLocation.Relation> lastActionRelations) {
        Objects.requireNonNull(groupType);
        this.groupType = groupType;
        this.lastActionRelations = lastActionRelations != null
                ? lastActionRelations
                : Stream.of(PlayerLocation.Relation.values()).filter(PlayerLocation.Relation::isOther)
                .collect(Collectors.toList());
    }
    protected CpgActionType(TileGroupType groupType) {
        this(groupType, null);
    }
    @Override
    protected int getActionTilesSize() {
        return 0;
    }

    @Override
    protected boolean isLegalActionWithPreconition(GameContextPlayerView context, Set<Tile> tiles) {
        return false;
    }

    @Override
    protected void doLegalAction(GameContext context, PlayerLocation location, Set<Tile> tiles) {

    }

    @Override
    public boolean canPass(GameContext context, PlayerLocation location) {
        return false;
    }
}
