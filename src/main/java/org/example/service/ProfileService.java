package org.example.service;


import org.example.container.ComponentContainer;
import org.example.controller.AdminController;
import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.repository.ProfileRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component

public class ProfileService {

    private ProfileRepository profileRepository;;
    private AdminController adminController;

    public void profileList() {
        List<Profile> profileList = profileRepository.getProfileList();

        for (Profile profile : profileList) {
            System.out.println(profile);
        }
    }

    public void changeProfileStatus(String phone) {
        Profile profile = profileRepository.getProfileByPhone(phone);
        if (profile == null) {
            System.out.println("Profile not found");
            return;
        }

        if (profile.getStatus().equals(GeneralStatus.ACTIVE)) {
            profileRepository.changeProfileStatus(phone, GeneralStatus.BLOCK);
        } else {
            profileRepository.changeProfileStatus(phone, GeneralStatus.ACTIVE);
        }
    }

    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileRepository getProfileRepository() {
        return profileRepository;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public AdminController getAdminController() {
        return adminController;
    }
}
