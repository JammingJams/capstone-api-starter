package org.yearup.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.yearup.models.Profile;
import org.yearup.repository.ProfileRepository;

@Service
@Transactional
public class ProfileService
{
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository)
    {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile)
    {
        return profileRepository.save(profile);
    }

    public Profile getProfileByUserId(int userId) {
        return profileRepository.findByUserId(userId);
    }

    public Profile update(Profile profile, int userId) {
        Profile existing = profileRepository.findByUserId(userId);

        existing.setAddress(profile.getAddress());
        existing.setCity(profile.getCity());
        existing.setEmail(profile.getEmail());
        existing.setPhone(profile.getPhone());
        existing.setState(profile.getState());
        existing.setFirstName(profile.getFirstName());
        existing.setLastName(profile.getLastName());
        existing.setZip(profile.getZip());

        return profileRepository.save(existing);
    }
}
