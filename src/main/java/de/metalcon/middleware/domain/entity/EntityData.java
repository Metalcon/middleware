package de.metalcon.middleware.domain.entity;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;
import de.metalcon.domain.Muid;
import de.metalcon.domain.Uid;
import de.metalcon.like.api.Direction;
import de.metalcon.like.api.Vote;
import de.metalcon.like.api.requests.LikeServerFollowsRequest;
import de.metalcon.like.api.requests.LikeServerGetLikesRequest;
import de.metalcon.like.api.requests.LikeServerRequest;
import de.metalcon.like.api.responses.LikeServerMuidListResponse;
import de.metalcon.like.api.responses.LikeServerVoteResponse;
import de.metalcon.middleware.domain.data.LikeData;

/**
 * basic identity class for Metalcon objects that can be identified via a MUID<br>
 * (and therefore is accessible via an URL)<br>
 * 
 * @see EntityType
 */
public class EntityData {

    /**
     * object identifier
     */
    private Muid muid;

    /**
     * Name to be printed on the web page
     */
    private String name;

    /**
     * URL to this identity
     */
    private String url;

    /**
     * Like statistics of this entity
     */
    private LikeData likeData;

    /**
     * create basic identify class
     * 
     * @param dispatcher
     *            Used to retrieve like button statistics
     * @param userID
     *            The session user
     * @param entityID
     *            The entity this data is stored for
     */
    public EntityData(
            final Dispatcher dispatcher,
            final Muid userID,
            final Muid entityID) {
        setMuid(entityID);

        likeData = getLikeCounts(dispatcher, userID, entityID);
    }

    /**
     * @return object identifier
     */
    public Muid getMuid() {
        return muid;
    }

    /**
     * @return object identifier
     */
    public final String getMuidSerialized() {
        return muid.toString();
    }

    /**
     * @param muid
     *            object identifier
     */
    public void setMuid(Muid muid) {
        this.muid = muid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LikeData getLikeData() {
        return likeData;
    }

    public void setLikeData(LikeData likeData) {
        this.likeData = likeData;
    }

    /**
     * Get all like button related statistics
     * 
     * @param dispatcher
     * @param uid
     * @return
     */
    public static LikeData getLikeCounts(
            final Dispatcher dispatcher,
            final Muid userID,
            final Uid uid) {
        final LikeData likeData = new LikeData();

        /*
         * Up votes of uid
         */
        LikeServerRequest req =
                new LikeServerGetLikesRequest(uid, Direction.INCOMING, Vote.UP);
        dispatcher.execute(req, new Callback<LikeServerMuidListResponse>() {

            @Override
            public void onSuccess(LikeServerMuidListResponse reply) {
                int num =
                        reply.getRawUids() == null
                                ? 0
                                : reply.getRawUids().length;
                likeData.setUpVoteNum(num);
            }
        });

        /*
         * Up votes of uid
         */
        req = new LikeServerGetLikesRequest(uid, Direction.INCOMING, Vote.DOWN);
        dispatcher.execute(req, new Callback<LikeServerMuidListResponse>() {

            @Override
            public void onSuccess(LikeServerMuidListResponse reply) {
                int num =
                        reply.getRawUids() == null
                                ? 0
                                : reply.getRawUids().length;
                likeData.setDownVoteNum(num);
            }
        });

        /*
         * Does user like uid?
         */
        req = new LikeServerFollowsRequest(userID, uid);
        dispatcher.execute(req, new Callback<LikeServerVoteResponse>() {

            @Override
            public void onSuccess(LikeServerVoteResponse reply) {
                likeData.setCurrentVote(reply.getVote());
            }
        });

        return likeData;
    }
}
