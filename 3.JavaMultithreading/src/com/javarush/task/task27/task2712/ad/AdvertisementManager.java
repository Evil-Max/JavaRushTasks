package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> availableVideos = new ArrayList<>();
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();
        for(Advertisement ad:storage.list()) {
            if (ad.getHits()>0)
            availableVideos.add(ad);
        }

        List<Advertisement> videosToShow = new ArrayList<>();
        List<Advertisement> bestVideos = null;

        int j,maxAmount=0,maxDuration=0,minSize=availableVideos.size();
        for(long i=1;i<(1<<availableVideos.size());i++) {
            videosToShow.clear();
            long n=i;
            j=0;
            while(n>0) {
                if (n%2==1) if (availableVideos.get(j).getHits()>0) videosToShow.add(availableVideos.get(j));
                n>>=1;
                j++;
            }
            int sumDuration=0,sumAmount=0;
            for(Advertisement ad:videosToShow) {
                sumDuration+=ad.getDuration();
                sumAmount+=ad.getAmountPerOneDisplaying()/**ad.getDuration()*/;
            }

            if (sumDuration<=timeSeconds) {
                if ((maxAmount<sumAmount)||
                        ((maxAmount==sumAmount)&&((maxDuration<sumDuration)||
                                ((maxDuration==sumDuration)&&(minSize>videosToShow.size()))))) {
                    maxAmount=sumAmount;
                    maxDuration=sumDuration;
                    minSize=videosToShow.size();
                    bestVideos = new ArrayList<>(videosToShow);
                }
            }
        }
        if (bestVideos!=null) {
            Collections.sort(bestVideos, new Comparator<Advertisement>() {
                @Override
                public int compare(Advertisement o1, Advertisement o2) {
                    if ((o1.getAmountPerOneDisplaying() - o2.getAmountPerOneDisplaying()) == 0) {
                        return (int) (o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration() - o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
                    } else {
                        return (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
                    }
                }
            });
            for (Advertisement ad : bestVideos) {
                ConsoleHelper.writeMessage(ad.getName() + " is displaying... "
                        + ad.getAmountPerOneDisplaying()
                        + ", " + ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration());
                ad.revalidate();
            }
        }
    }
}
