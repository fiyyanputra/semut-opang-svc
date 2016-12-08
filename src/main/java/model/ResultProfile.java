package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

/**
 * Created by fiyyanp on 10/26/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "success",
        "Message",
        "Profile"
})
public class ResultProfile {
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Profile")
    private Profile profile;

    /**
     *
     * @return
     * The success
     */
    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     * The message
     */
    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The Message
     */
    @JsonProperty("Message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The profile
     */
    @JsonProperty("Profile")
    public Profile getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     * The Profile
     */
    @JsonProperty("Profile")
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public class Profile {

        @JsonProperty("ID")
        private String iD;
        @JsonProperty("Name")
        private String name;
        @JsonProperty("Email")
        private String email;
        @JsonProperty("CountryCode")
        private String countryCode;
        @JsonProperty("PhoneNumber")
        private String phoneNumber;
        @JsonProperty("Gender")
        private String gender;
        @JsonProperty("Birthday")
        private String birthday;
        @JsonProperty("Joindate")
        private String joindate;
        @JsonProperty("Poin")
        private String poin;
        @JsonProperty("Poinlevel")
        private String poinlevel;
        @JsonProperty("Visibility")
        private String visibility;
        @JsonProperty("Verified")
        private String verified;
        @JsonProperty("AvatarID")
        private String avatarID;
        @JsonProperty("Reputation")
        private String reputation;
        @JsonProperty("PushID")
        private Object pushID;
        @JsonProperty("ID_role")
        private Object iDRole;
        @JsonProperty("Plat_motor")
        private Object platMotor;
        @JsonProperty("ID_ktp")
        private Object iDKtp;

        /**
         * @return The iD
         */
        @JsonProperty("ID")
        public String getID() {
            return iD;
        }

        /**
         * @param iD The ID
         */
        @JsonProperty("ID")
        public void setID(String iD) {
            this.iD = iD;
        }

        /**
         * @return The name
         */
        @JsonProperty("Name")
        public String getName() {
            return name;
        }

        /**
         * @param name The Name
         */
        @JsonProperty("Name")
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return The email
         */
        @JsonProperty("Email")
        public String getEmail() {
            return email;
        }

        /**
         * @param email The Email
         */
        @JsonProperty("Email")
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * @return The countryCode
         */
        @JsonProperty("CountryCode")
        public String getCountryCode() {
            return countryCode;
        }

        /**
         * @param countryCode The CountryCode
         */
        @JsonProperty("CountryCode")
        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        /**
         * @return The phoneNumber
         */
        @JsonProperty("PhoneNumber")
        public String getPhoneNumber() {
            return phoneNumber;
        }

        /**
         * @param phoneNumber The PhoneNumber
         */
        @JsonProperty("PhoneNumber")
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        /**
         * @return The gender
         */
        @JsonProperty("Gender")
        public String getGender() {
            return gender;
        }

        /**
         * @param gender The Gender
         */
        @JsonProperty("Gender")
        public void setGender(String gender) {
            this.gender = gender;
        }

        /**
         * @return The birthday
         */
        @JsonProperty("Birthday")
        public String getBirthday() {
            return birthday;
        }

        /**
         * @param birthday The Birthday
         */
        @JsonProperty("Birthday")
        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        /**
         * @return The joindate
         */
        @JsonProperty("Joindate")
        public String getJoindate() {
            return joindate;
        }

        /**
         * @param joindate The Joindate
         */
        @JsonProperty("Joindate")
        public void setJoindate(String joindate) {
            this.joindate = joindate;
        }

        /**
         * @return The poin
         */
        @JsonProperty("Poin")
        public String getPoin() {
            return poin;
        }

        /**
         * @param poin The Poin
         */
        @JsonProperty("Poin")
        public void setPoin(String poin) {
            this.poin = poin;
        }

        /**
         * @return The poinlevel
         */
        @JsonProperty("Poinlevel")
        public String getPoinlevel() {
            return poinlevel;
        }

        /**
         * @param poinlevel The Poinlevel
         */
        @JsonProperty("Poinlevel")
        public void setPoinlevel(String poinlevel) {
            this.poinlevel = poinlevel;
        }

        /**
         * @return The visibility
         */
        @JsonProperty("Visibility")
        public String getVisibility() {
            return visibility;
        }

        /**
         * @param visibility The Visibility
         */
        @JsonProperty("Visibility")
        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }

        /**
         * @return The verified
         */
        @JsonProperty("Verified")
        public String getVerified() {
            return verified;
        }

        /**
         * @param verified The Verified
         */
        @JsonProperty("Verified")
        public void setVerified(String verified) {
            this.verified = verified;
        }

        /**
         * @return The avatarID
         */
        @JsonProperty("AvatarID")
        public String getAvatarID() {
            return avatarID;
        }

        /**
         * @param avatarID The AvatarID
         */
        @JsonProperty("AvatarID")
        public void setAvatarID(String avatarID) {
            this.avatarID = avatarID;
        }

        /**
         * @return The reputation
         */
        @JsonProperty("Reputation")
        public String getReputation() {
            return reputation;
        }

        /**
         * @param reputation The Reputation
         */
        @JsonProperty("Reputation")
        public void setReputation(String reputation) {
            this.reputation = reputation;
        }

        /**
         * @return The pushID
         */
        @JsonProperty("PushID")
        public Object getPushID() {
            return pushID;
        }

        /**
         * @param pushID The PushID
         */
        @JsonProperty("PushID")
        public void setPushID(Object pushID) {
            this.pushID = pushID;
        }

        /**
         * @return The iDRole
         */
        @JsonProperty("ID_role")
        public Object getIDRole() {
            return iDRole;
        }

        /**
         * @param iDRole The ID_role
         */
        @JsonProperty("ID_role")
        public void setIDRole(Object iDRole) {
            this.iDRole = iDRole;
        }

        /**
         * @return The platMotor
         */
        @JsonProperty("Plat_motor")
        public Object getPlatMotor() {
            return platMotor;
        }

        /**
         * @param platMotor The Plat_motor
         */
        @JsonProperty("Plat_motor")
        public void setPlatMotor(Object platMotor) {
            this.platMotor = platMotor;
        }

        /**
         * @return The iDKtp
         */
        @JsonProperty("ID_ktp")
        public Object getIDKtp() {
            return iDKtp;
        }

        /**
         * @param iDKtp The ID_ktp
         */
        @JsonProperty("ID_ktp")
        public void setIDKtp(Object iDKtp) {
            this.iDKtp = iDKtp;
        }
    }
}
