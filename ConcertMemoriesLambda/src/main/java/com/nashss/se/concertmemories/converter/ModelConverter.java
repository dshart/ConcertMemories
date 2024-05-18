package com.nashss.se.concertmemories.converter;

import com.nashss.se.concertmemories.models.ConcertModel;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import java.util.ArrayList;
import java.util.List;

//
///**
// * Converts between Data models and the representation we want to return in the result.
// */
public class ModelConverter {
    /**
     * Converts a provided {@link Concert} into a {@link ConcertModel} representation.
     * @param concert the concert to convert
     * @return the converted concert
     */
    public ConcertModel toConcertModel(Concert concert) {
//        List<String> tags = null;
//        if (playlist.getTags() != null) {
//            tags = new ArrayList<>(playlist.getTags());
//        }
//
        return null;
//        return PlaylistModel.builder()
//                .withId(playlist.getId())
//                .withName(playlist.getName())
//                .withCustomerId(playlist.getCustomerId())
//                .withSongCount(playlist.getSongCount())
//                .withTags(tags)
//                .build();
    }
//
//    /**
//     * Converts a provided AlbumTrack into a SongModel representation.
//     * @param albumTrack the AlbumTrack to convert to SongModel
//     * @return the converted SongModel with fields mapped from albumTrack
//     */
//    public SongModel toSongModel(AlbumTrack albumTrack) {
//        return SongModel.builder()
//                .withAsin(albumTrack.getAsin())
//                .withTrackNumber(albumTrack.getTrackNumber())
//                .withAlbum(albumTrack.getAlbumName())
//                .withTitle(albumTrack.getSongTitle())
//                .build();
//    }
//
//    /**
//     * Converts a list of AlbumTracks to a list of SongModels.
//     * @param albumTracks The AlbumTracks to convert to SongModels
//     * @return The converted list of SongModels
//     */
//    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
//        List<SongModel> songModels = new ArrayList<>();
//        for (AlbumTrack albumTrack : albumTracks) {
//            songModels.add(toSongModel(albumTrack));
//        }
//
    // return songModels;
//    }
}
