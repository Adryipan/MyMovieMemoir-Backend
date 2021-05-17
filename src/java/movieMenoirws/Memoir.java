/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieMenoirws;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adrian
 */
@Entity
@Table(name = "MEMOIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memoir.findAll", query = "SELECT m FROM Memoir m")
    , @NamedQuery(name = "Memoir.findByMemId", query = "SELECT m FROM Memoir m WHERE m.memId = :memId")
    , @NamedQuery(name = "Memoir.findByMovieName", query = "SELECT m FROM Memoir m WHERE UPPER(m.movieName) = UPPER(:movieName)")
    , @NamedQuery(name = "Memoir.findByReleaseDate", query = "SELECT m FROM Memoir m WHERE m.releaseDate = :releaseDate")
    , @NamedQuery(name = "Memoir.findByWatchTime", query = "SELECT m FROM Memoir m WHERE m.watchTime = :watchTime")
    , @NamedQuery(name = "Memoir.findByWatchDate", query = "SELECT m FROM Memoir m WHERE m.watchDate = :watchDate")
    , @NamedQuery(name = "Memoir.findByComment", query = "SELECT m FROM Memoir m WHERE m.comment = :comment")
    , @NamedQuery(name = "Memoir.findByRating", query = "SELECT m FROM Memoir m WHERE m.rating = :rating")
    , @NamedQuery(name = "Memoir.findByMovieNameAndCinemaSurburb", query = "SELECT m FROM Memoir m WHERE UPPER(m.movieName) = UPPER(:movieName) AND m.cinemaId.suburb = :suburb")})
public class Memoir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MEM_ID")
    private Integer memId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WATCH_TIME")
    @Temporal(TemporalType.TIME)
    private Date watchTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WATCH_DATE")
    @Temporal(TemporalType.DATE)
    private Date watchDate;
    @Size(max = 300)
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "RATING")
    private String rating;
    @JoinColumn(name = "CINEMA_ID", referencedColumnName = "CINEMA_ID")
    @ManyToOne
    private Cinema cinemaId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Person userId;

    public Memoir() {
    }

    public Memoir(Integer memId) {
        this.memId = memId;
    }

    public Memoir(Integer memId, String movieName, Date releaseDate, Date watchTime, Date watchDate) {
        this.memId = memId;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.watchTime = watchTime;
        this.watchDate = watchDate;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(Date watchTime) {
        this.watchTime = watchTime;
    }

    public Date getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Cinema getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Cinema cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Person getUserId() {
        return userId;
    }

    public void setUserId(Person userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memId != null ? memId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memoir)) {
            return false;
        }
        Memoir other = (Memoir) object;
        if ((this.memId == null && other.memId != null) || (this.memId != null && !this.memId.equals(other.memId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "movieMenoirws.Memoir[ memId=" + memId + " ]";
    }
    
}
