package com.example.android.musicplayerproject;

import android.util.Log;

import java.util.ArrayList;


public class Library {
    // creating main ArrayLists to hold Artist, Album and Song classes
    private ArrayList<Artist> libraryArtists;
    private ArrayList<Album> libraryAlbums;
    private ArrayList<Song> librarySongs;

    // constuctor
    public Library() {
        this.librarySongs = sortSongs(makeSongs());
        this.libraryAlbums = sortAlbums(makeAlbums());
        this.libraryArtists = sortArtists(makeArtists());
    }

    ////////////////////////////////////////////////
    /////////// Getter methods to return specific objects from main ArrayLists - Artists, Albums and Songs
    //////////////////////////////////////////////
    public Artist getArtist(String artist) {
        Artist artistToReturn = null;
        for (int i = 0; i < libraryArtists.size(); i++) {
            if (libraryArtists.get(i).getArtistName().equals(artist)) {
                artistToReturn = libraryArtists.get(i);
                break;
            }
        }
        return artistToReturn;
    }

    public Album getAlbum(String album) {
        Album albumToReturn = null;
        for (int i = 0; i < libraryAlbums.size(); i++) {
            if (libraryAlbums.get(i).getTitle().equals(album)) {
                albumToReturn = libraryAlbums.get(i);
                break;
            }
        }
        return albumToReturn;
    }

    public Song getSong(String title, String artist) {
        Song song = null;
        for (int i = 0; i < librarySongs.size(); i++) {
            if (librarySongs.get(i).getTitle().equals(title) && librarySongs.get(i).getArtist().equals(artist)) {
                song = librarySongs.get(i);
                break;
            }
        }
        return song;
    }
    ////////////////////////////////////////////////
    /////////// Getter methods to return main ArrayLists - Artists, Albums and Songs
    //////////////////////////////////////////////

    public ArrayList<Artist> getLibraryArtists() {
        return libraryArtists;
    }

    public ArrayList<Album> getLibraryAlbums() {
        return libraryAlbums;
    }

    public ArrayList<Song> getLibrarySongs() {
        return librarySongs;
    }

    /////////////////////////////////////////////////
    //////////// Alphabetical array sorting methods to sort main ArrayLists into alphabetical order
    ////////////////////////////////////////////////
    private ArrayList<Song> sortSongs(ArrayList<Song> songs) {
        boolean unfinished = true;
        while (unfinished) {
            unfinished = false;
            for (int i = 0; i < songs.size() - 1; i++) {
                Song swapper = songs.get(i);
                int compare = songs.get(i).getTitle().compareTo(songs.get(i + 1).getTitle());
                if (compare > 0) {
                    songs.set(i, songs.get(i + 1));
                    songs.set(i + 1, swapper);
                    unfinished = true;
                }
            }
        }
        return songs;
    }

    public ArrayList<Album> sortAlbums(ArrayList<Album> albums) {
        boolean unfinished = true;
        while (unfinished) {

            unfinished = false;

            for (int i = 0; i < albums.size() - 1; i++) {
                Album swapper = albums.get(i);
                int compare = albums.get(i).getTitle().compareTo(albums.get(i + 1).getTitle());
                if (compare > 0) {
                    albums.set(i, albums.get(i + 1));
                    albums.set(i + 1, swapper);
                    unfinished = true;
                }
            }
        }
        return albums;
    }

    private ArrayList<Artist> sortArtists(ArrayList<Artist> artists) {
        boolean unfinished = true;
        while (unfinished) {

            unfinished = false;

            for (int i = 0; i < artists.size() - 1; i++) {
                Artist swapper = artists.get(i);
                int compare = artists.get(i).getArtistName().compareTo(artists.get(i + 1).getArtistName());
                if (compare > 0) {
                    artists.set(i, artists.get(i + 1));
                    artists.set(i + 1, swapper);
                    unfinished = true;
                }
            }
        }
        return artists;
    }

    /////////////////////////////////////////////////
    //////////// inner classes Artist, Album and Song
    ////////////////////////////////////////////////
    public class Artist {
        private String artistName;
        private ArrayList<Album> artistAlbums;
        private int imageId;

        private Artist(String artistName, int imageId) {
            this.artistName = artistName;
            this.artistAlbums = makeArtistAlbums(artistName);
            this.imageId = imageId;
        }

        private ArrayList<Album> makeArtistAlbums(String artistName) {
            ArrayList<Album> albums = new ArrayList<>();

            for (int i = 0; i < libraryAlbums.size(); i++) {
                if (libraryAlbums.get(i).getArtist().equals(artistName)) {
                    albums.add(libraryAlbums.get(i));
                }
            }
            return albums;
        }

        public String getArtistName() {
            return artistName;
        }

        public ArrayList<Album> getArtistAlbums() {
            return artistAlbums;
        }

        public int getImageId() {
            return imageId;
        }
    }


    public class Album {
        private String artist;
        private String title;
        private ArrayList<Song> albumSongs;
        private int imageId;

        private Album(String artist, String title, int imageId) {
            this.artist = artist;
            this.title = title;
            this.albumSongs = makeAlbum();
            this.imageId = imageId;
        }

        private ArrayList<Song> makeAlbum() {
            ArrayList<Song> album = new ArrayList<>();
            for (int i = 0; i < librarySongs.size(); i++) {
                if (librarySongs.get(i).getAlbum().equals(title)) {
                    album.add(librarySongs.get(i));
                }
            }
            return album;
        }

        public int getImageId() {
            return imageId;
        }

        public String getArtist() {
            return artist;
        }

        public String getTitle() {
            return title;
        }

        public ArrayList<Song> getAlbumSongs() {
            return albumSongs;
        }
    }

    public class Song {
        private String artist;
        private String album;
        private String title;

        private Song(String artist, String album, String title) {
            this.artist = artist;
            this.album = album;
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public String getAlbum() {
            return album;
        }

        public String getTitle() {
            return title;
        }
    }

    ///////////////////////////////////////////////
    // Methods for constructing library arrays
    //////////////////////////////////////////////
    private ArrayList<Artist> makeArtists() {

        ArrayList<Artist> artists = new ArrayList<>();

        artists.add(new Artist("Tacitus", R.drawable.tacitus));
        artists.add(new Artist("Dorothy Stewart", R.drawable.dorothy_stewart));
        artists.add(new Artist("Elizabeth Bentley", R.drawable.elisabeth_bentley));
        artists.add(new Artist("Hippolyte", R.drawable.hippolyte));
        artists.add(new Artist("The Broken Doors", R.drawable.the_broken_doors));
        artists.add(new Artist("Jack Fraser", R.drawable.jack_fraser));
        artists.add(new Artist("Emily Howard", R.drawable.emily_howard));
        artists.add(new Artist("Blessed", R.drawable.blessed));
        artists.add(new Artist("Abundant", R.drawable.abundant));
        artists.add(new Artist("Yellow House", R.drawable.yellow_house));
        artists.add(new Artist("Europe", R.drawable.europe));
        artists.add(new Artist("The Enthusiasts", R.drawable.the_enthusiasts));
        artists.add(new Artist("Blunt Look", R.drawable.blunt_look));

        return artists;
    }


    private ArrayList<Album> makeAlbums() {
        ArrayList<Album> albums = new ArrayList<>();

        albums.add(new Album("Tacitus", "The Conspirators", R.drawable.tacitus_the_conspirators));
        albums.add(new Album("Tacitus", "Geography", R.drawable.tacitus_geography));
        albums.add(new Album("Dorothy Stewart", "The Forgotten Maze", R.drawable.dorothy_stewart_the_forgotten_maze));
        albums.add(new Album("Dorothy Stewart", "Dream Life", R.drawable.dorothy_stewart_dream_life));
        albums.add(new Album("Elizabeth Bentley", "Under Blue Skies", R.drawable.elisabeth_bentley_under_the_blue_skies));
        albums.add(new Album("Elizabeth Bentley", "Hypnotic", R.drawable.elisabeth_bentley_hypnotic));
        albums.add(new Album("Elizabeth Bentley", "The Last Call", R.drawable.elisabeth_bentley_the_last_call));
        albums.add(new Album("Hippolyte", "Into the Light", R.drawable.hippolyte_into_the_light));
        albums.add(new Album("Hippolyte", "Memories", R.drawable.hippolyte_memories));
        albums.add(new Album("The Broken Doors", "Feeling", R.drawable.the_broken_doors_feeling));
        albums.add(new Album("Jack Fraser", "Energy", R.drawable.jack_fraser_energy));
        albums.add(new Album("Jack Fraser", "Magnificence", R.drawable.jack_fraser_magnificence));
        albums.add(new Album("Emily Howard", "Wild Horses and Dogs", R.drawable.emily_howard_wild_horses_and_dogs));
        albums.add(new Album("Blessed", "A Picture of This", R.drawable.blessed_a_picture_of_this));
        albums.add(new Album("Abundant", "Fashion Lover", R.drawable.abundant_fashionlover));
        albums.add(new Album("Abundant", "Mama's Secret", R.drawable.abundant_mamas_secret));
        albums.add(new Album("Yellow House", "Rain Dance", R.drawable.yellow_house_raindance));
        albums.add(new Album("Yellow House", "Pillaged", R.drawable.yellow_house_pillaged));
        albums.add(new Album("Yellow House", "A Word of Warning", R.drawable.yellow_house_a_word_of_warning));
        albums.add(new Album("Europe", "Borders", R.drawable.europe_borders));
        albums.add(new Album("Europe", "Luther and the Cow", R.drawable.europe_luther_and_the_cow));
        albums.add(new Album("The Enthusiasts", "Army of the West", R.drawable.the_enthusiasts_army_of_the_west));
        albums.add(new Album("Blunt Look", "Nobody's Baby", R.drawable.blunt_look_nobodys_baby));
        albums.add(new Album("Blunt Look", "Hurricanes", R.drawable.blunt_look_hurricanes));

        return albums;
    }

    private ArrayList<Song> makeSongs() {
        ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song("Tacitus", "The Conspirators", "Unison"));
        songs.add(new Song("Tacitus", "The Conspirators", "Out of Jail"));
        songs.add(new Song("Tacitus", "The Conspirators", "Electric Teapot"));
        songs.add(new Song("Tacitus", "The Conspirators", "Old School"));

        songs.add(new Song("Tacitus", "Geography", "Maps"));
        songs.add(new Song("Tacitus", "Geography", "The Edge of the World"));
        songs.add(new Song("Tacitus", "Geography", "Time"));
        songs.add(new Song("Tacitus", "Geography", "Countdown"));
        songs.add(new Song("Tacitus", "Geography", "Lady Grey"));

        songs.add(new Song("Dorothy Stewart", "The Forgotten Maze", "Minotaur"));
        songs.add(new Song("Dorothy Stewart", "The Forgotten Maze", "Concrete Spaces"));
        songs.add(new Song("Dorothy Stewart", "The Forgotten Maze", "Deep Blue"));
        songs.add(new Song("Dorothy Stewart", "The Forgotten Maze", "Far Cries"));
        songs.add(new Song("Dorothy Stewart", "The Forgotten Maze", "Old Tree Roots"));
        songs.add(new Song("Dorothy Stewart", "The Forgotten Maze", "Sacrifice"));
        songs.add(new Song("Dorothy Stewart", "The Forgotten Maze", "Troubles"));

        songs.add(new Song("Dorothy Stewart", "Dream Life", "Dream Life"));
        songs.add(new Song("Dorothy Stewart", "Dream Life", "Rope the Table Down"));
        songs.add(new Song("Dorothy Stewart", "Dream Life", "Gas Leaks"));
        songs.add(new Song("Dorothy Stewart", "Dream Life", "Oceans in the West"));
        songs.add(new Song("Dorothy Stewart", "Dream Life", "Laughing"));
        songs.add(new Song("Dorothy Stewart", "Dream Life", "God knows a Joker"));

        songs.add(new Song("Elizabeth Bentley", "Under Blue Skies", "Time Warp"));
        songs.add(new Song("Elizabeth Bentley", "Under Blue Skies", "Milky Skies"));
        songs.add(new Song("Elizabeth Bentley", "Under Blue Skies", "Clouds"));
        songs.add(new Song("Elizabeth Bentley", "Under Blue Skies", "Raining in New York"));
        songs.add(new Song("Elizabeth Bentley", "Under Blue Skies", "Freezing in London"));
        songs.add(new Song("Elizabeth Bentley", "Under Blue Skies", "A Beer in Majorca"));

        songs.add(new Song("Elizabeth Bentley", "Hypnotic", "Watch this Space"));
        songs.add(new Song("Elizabeth Bentley", "Hypnotic", "Other Lives"));
        songs.add(new Song("Elizabeth Bentley", "Hypnotic", "Cranked Up"));
        songs.add(new Song("Elizabeth Bentley", "Hypnotic", "Explosive"));
        songs.add(new Song("Elizabeth Bentley", "Hypnotic", "I lost the Plot"));

        songs.add(new Song("Elizabeth Bentley", "The Last Call", "A Song"));
        songs.add(new Song("Elizabeth Bentley", "The Last Call", "Another Song"));
        songs.add(new Song("Elizabeth Bentley", "The Last Call", "Another Generic Song"));
        songs.add(new Song("Elizabeth Bentley", "The Last Call", "Song for the Need of One"));
        songs.add(new Song("Elizabeth Bentley", "The Last Call", "I'm Bored of Writing Song Titles"));

        songs.add(new Song("Hippolyte", "Memories", "This is not a Hippo"));
        songs.add(new Song("Hippolyte", "Memories", "Ancient Greek"));
        songs.add(new Song("Hippolyte", "Memories", "Some Island"));
        songs.add(new Song("Hippolyte", "Memories", "Remains"));

        songs.add(new Song("Hippolyte", "Into the Light", "Traffic Lights"));
        songs.add(new Song("Hippolyte", "Into the Light", "Northern Lights"));
        songs.add(new Song("Hippolyte", "Into the Light", "Fairy Lights"));
        songs.add(new Song("Hippolyte", "Into the Light", "A Torch Light"));
        songs.add(new Song("Hippolyte", "Into the Light", "Car Lights"));
        songs.add(new Song("Hippolyte", "Into the Light", "Into the Light"));

        songs.add(new Song("The Broken Doors", "Feeling", "Sense of Touch"));
        songs.add(new Song("The Broken Doors", "Feeling", "Emotionless"));
        songs.add(new Song("The Broken Doors", "Feeling", "Furious"));
        songs.add(new Song("The Broken Doors", "Feeling", "Mixed Emotions"));
        songs.add(new Song("The Broken Doors", "Feeling", "As if it Matters"));
        songs.add(new Song("The Broken Doors", "Feeling", "The Ego of an Artist"));

        songs.add(new Song("Jack Fraser", "Energy", "A Song"));
        songs.add(new Song("Jack Fraser", "Energy", "My Favourite Song"));
        songs.add(new Song("Jack Fraser", "Energy", "Ha Ha Another Song"));
        songs.add(new Song("Jack Fraser", "Energy", "Old McDonald"));
        songs.add(new Song("Jack Fraser", "Energy", "Song ++"));
        songs.add(new Song("Jack Fraser", "Energy", "Christmas Song"));
        songs.add(new Song("Jack Fraser", "Energy", "Old Song"));
        songs.add(new Song("Jack Fraser", "Energy", "New Song"));

        songs.add(new Song("Jack Fraser", "Magnificence", "Last Supper"));
        songs.add(new Song("Jack Fraser", "Magnificence", "Religion and Art"));
        songs.add(new Song("Jack Fraser", "Magnificence", "Deep Blue Cities"));
        songs.add(new Song("Jack Fraser", "Magnificence", "Picasso"));
        songs.add(new Song("Jack Fraser", "Magnificence", "Another old Painter"));

        songs.add(new Song("Emily Howard", "Wild Horses and Dogs", "The Horse Found a Dog"));
        songs.add(new Song("Emily Howard", "Wild Horses and Dogs", "The Dog Ran Off"));
        songs.add(new Song("Emily Howard", "Wild Horses and Dogs", "The Horse Ran Off"));
        songs.add(new Song("Emily Howard", "Wild Horses and Dogs", "The Horse Chased the Dog"));
        songs.add(new Song("Emily Howard", "Wild Horses and Dogs", "The Dog Ran for it's Life"));
        songs.add(new Song("Emily Howard", "Wild Horses and Dogs", "Aren't Horses Great?"));

        songs.add(new Song("Blessed", "A Picture of This", "A Picture of This"));
        songs.add(new Song("Blessed", "A Picture of This", "A Car in the Photo"));
        songs.add(new Song("Blessed", "A Picture of This", "Seaside Towns"));
        songs.add(new Song("Blessed", "A Picture of This", "Windswept"));
        songs.add(new Song("Blessed", "A Picture of This", "Liverpool got Beat"));
        songs.add(new Song("Blessed", "A Picture of This", "Manchester Won the League"));

        songs.add(new Song("Abundant", "Fashion Lover", "Lots of Stuff Here"));
        songs.add(new Song("Abundant", "Fashion Lover", "Stuff in the Atic"));
        songs.add(new Song("Abundant", "Fashion Lover", "Bears"));
        songs.add(new Song("Abundant", "Fashion Lover", "Ghost Train"));
        songs.add(new Song("Abundant", "Fashion Lover", "Bridges"));
        songs.add(new Song("Abundant", "Fashion Lover", "A Good Cup of Coffee"));
        songs.add(new Song("Abundant", "Fashion Lover", "Sandwich Maker"));
        songs.add(new Song("Abundant", "Fashion Lover", "Plastered"));

        songs.add(new Song("Abundant", "Mama's Secret", "Trump"));
        songs.add(new Song("Abundant", "Mama's Secret", "Putin"));
        songs.add(new Song("Abundant", "Mama's Secret", "Parliament"));
        songs.add(new Song("Abundant", "Mama's Secret", "Taxes"));
        songs.add(new Song("Abundant", "Mama's Secret", "Economics"));
        songs.add(new Song("Abundant", "Mama's Secret", "We Love Banks"));

        songs.add(new Song("Yellow House", "Rain Dance", "It's Dry Here"));
        songs.add(new Song("Yellow House", "Rain Dance", "I Wish it would Rain"));
        songs.add(new Song("Yellow House", "Rain Dance", "Raining Now"));
        songs.add(new Song("Yellow House", "Rain Dance", "Great Dance"));
        songs.add(new Song("Yellow House", "Rain Dance", "It's too Wet Now"));

        songs.add(new Song("Yellow House", "Pillaged", "They Stole the Fridge"));
        songs.add(new Song("Yellow House", "Pillaged", "Where's the Cooker?"));
        songs.add(new Song("Yellow House", "Pillaged", "My Phone got Nicked"));
        songs.add(new Song("Yellow House", "Pillaged", "Stolen Money"));
        songs.add(new Song("Yellow House", "Pillaged", "Nothing Left"));
        songs.add(new Song("Yellow House", "Pillaged", "Money is Gone"));
        songs.add(new Song("Yellow House", "Pillaged", "Can't Find the Keys"));
        songs.add(new Song("Yellow House", "Pillaged", "Did you see the Mess?"));
        songs.add(new Song("Yellow House", "Pillaged", "Under the Stairs"));

        songs.add(new Song("Yellow House", "A Word of Warning", "Watch Out!"));
        songs.add(new Song("Yellow House", "A Word of Warning", "Warning"));
        songs.add(new Song("Yellow House", "A Word of Warning", "Something Dropped"));
        songs.add(new Song("Yellow House", "A Word of Warning", "All Night"));
        songs.add(new Song("Yellow House", "A Word of Warning", "Lost and Found"));
        songs.add(new Song("Yellow House", "A Word of Warning", "A Word of Warning"));

        songs.add(new Song("Europe", "Borders", "Germany"));
        songs.add(new Song("Europe", "Borders", "Belgium"));
        songs.add(new Song("Europe", "Borders", "France"));
        songs.add(new Song("Europe", "Borders", "Spain"));
        songs.add(new Song("Europe", "Borders", "UK"));
        songs.add(new Song("Europe", "Borders", "Ireland"));
        songs.add(new Song("Europe", "Borders", "Holland"));

        songs.add(new Song("Europe", "Luther and the Cow", "The Cow"));
        songs.add(new Song("Europe", "Luther and the Cow", "Luther"));
        songs.add(new Song("Europe", "Luther and the Cow", "I hope they don't get Angry"));
        songs.add(new Song("Europe", "Luther and the Cow", "I never Wrote That"));
        songs.add(new Song("Europe", "Luther and the Cow", "Angry Mobs"));
        songs.add(new Song("Europe", "Luther and the Cow", "Unfortunate Event"));

        songs.add(new Song("The Enthusiasts", "Army of the West", "Fishing"));
        songs.add(new Song("The Enthusiasts", "Army of the West", "On the March"));
        songs.add(new Song("The Enthusiasts", "Army of the West", "Uncle Sam"));
        songs.add(new Song("The Enthusiasts", "Army of the West", "Windmills"));
        songs.add(new Song("The Enthusiasts", "Army of the West", "Ok Ok Ok"));
        songs.add(new Song("The Enthusiasts", "Army of the West", "Looks like a Problem"));
        songs.add(new Song("The Enthusiasts", "Army of the West", "Open all Hours"));

        songs.add(new Song("Blunt Look", "Nobody's Baby", "Shut Up"));
        songs.add(new Song("Blunt Look", "Nobody's Baby", "Over the Hill"));
        songs.add(new Song("Blunt Look", "Nobody's Baby", "Caught in the act"));
        songs.add(new Song("Blunt Look", "Nobody's Baby", "Slight of Hand"));
        songs.add(new Song("Blunt Look", "Nobody's Baby", "The only One"));
        songs.add(new Song("Blunt Look", "Nobody's Baby", "Torment"));

        songs.add(new Song("Blunt Look", "Hurricanes", "It's Windy Here"));
        songs.add(new Song("Blunt Look", "Hurricanes", "Hurricanes have Names"));
        songs.add(new Song("Blunt Look", "Hurricanes", "Windy"));
        songs.add(new Song("Blunt Look", "Hurricanes", "What was that?"));
        songs.add(new Song("Blunt Look", "Hurricanes", "Sea is Rising"));
        songs.add(new Song("Blunt Look", "Hurricanes", "Flying Trees"));
        songs.add(new Song("Blunt Look", "Hurricanes", "The Roof is off"));


        return songs;
    }
}








































