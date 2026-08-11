

#ifndef NIL_SYSTEM_H
#define NIL_SYSTEM_H

#include <string>
#include <vector>

// --- Class: Sponsor ---
class Sponsor {
private:
    std::string companyName;
    int prestigeLevel;

public:
    Sponsor(std::string name, int prestige);
    void generateOffer(); // Method Stub
};

// --- Class: NIL_Deal ---
class NIL_Deal {
private:
    std::string brandName;
    double payoutAmount;
    int durationWeeks;

public:
    NIL_Deal(std::string brand, double payout, int duration);
    void activateDeal();
    double getPayout() const; // Getter
};

// --- Class: Athlete ---
class Athlete {
private:
    std::string name;
    std::string position;
    int influenceRating;
    int morale;
    std::vector<NIL_Deal> activeDeals;

public:
    Athlete(std::string n, std::string pos);
    void calculateMarketValue();
    void updateMorale(int change);
   
    // Getters and Setters
    int getInfluence() const { return influenceRating; }
    void setInfluence(int val) { influenceRating = val; }
};

// --- Class: Recruit ---
class Recruit {
private:
    std::string prospectName;
    int starRating;
    int interestLevel;

public:
    Recruit(std::string name, int stars);
    void evaluateNILPotential(int schoolPrestige);
    void commitToSchool();
};

// --- Class: ProgramBrand ---
class ProgramBrand {
private:
    std::string schoolName;
    int prestige;
    std::vector<Recruit> prospectBoard;

public:
    ProgramBrand(std::string name);
    void boostRecruitingPower();
    void updatePrestige(int score);
};

#endif