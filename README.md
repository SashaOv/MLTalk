# Agile Machine Learning with Scalding and scikit-learn

A talk delivered to [QCON San Francisco 2014](http://qconsf.com/tutorial/agile-machine-learning-scalding-and-scikit-learn).

## Prerequisites

- Working [Java](http://www.java.com/) installation (ideally, 1.7 and above)
- Python packages listed below. The easiest way to get them would be to use prepackaged distribution. 
  I use [Anaconda](https://store.continuum.io/cshop/anaconda/) , 
  [Canopy](http://www.enthought.com/products/canopy) may work as well:
    - [iPython](http://ipython.org/) and prerequisites.
    - [scikit-learn](http://scikit-learn.org/) and prerequisites.
    - [mathplotlib](http://matplotlib.org/) and prerequisites. On Mac, you should be careful to install the "framework version" so that `%mathplotlib inline` instruction with iPython notebook will work
- Few gigabytes of disk space

**Note** Anaconda is a big download (>1G), please prepare it in advance.

## Data
We are using [Million Song Dataset](http://labrosa.ee.columbia.edu/millionsong/). We are only using the following 
data from the subset:

- [Track Metadata](http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/track_metadata.db)
    - The CSV export of this database is available [in this Box.com folder](https://app.box.com/s/e99lauqs8kdfxhh3fe5s)
- [Million Song Subset Triplet Data](http://static.echonest.com/millionsongsubset_full.tar.gz)

## Getting started

Clone Github project:

     > git clone https://github.com/SashaOv/MLTalk.git
     > cd MLTalk
     
(Optional) Copy gradle dependencies to your installation from the flash drive
 
     > rsync -arv /where/is/gradle-home/ ~/.gradle
     
Execute build:

     > ./gradlew clean build
     
Run the sampler program:

     > cd acquire
     > ../gradlew run

