from django.db import models


USER_TYPES = (('INDIVIDUAL', 'Individual'), ('OFFICER', 'Officer'), ('ORGANIZATION', 'Organisation'));
GENDER_CHOICES = (('MALE', 'Male'), ('FEMALE', 'Female'));
REDRESSED_CHOICES = (('N', 'NOT REDRESSED'), ('R', 'REDRESSED'));
SECTOR = (('Public', 'Public'), ('Private', 'Private'));

class Users(models.Model):
    ID = models.AutoField(primary_key=True)
    EMAIL = models.EmailField();
    PASSWORD = models.CharField(max_length=255)
    USERNAME = models.CharField(max_length=50)
    USERTYPE = models.CharField(max_length=20, choices=USER_TYPES)

    def __unicode__(self):
        return u'%s' % (self.USERNAME)

    class Meta:
        db_table = "USER"


class Client(models.Model):  
    ID = models.AutoField(primary_key=True)
    CL_USERID_ID = models.ForeignKey(Users, verbose_name="User", db_column="CL_USERID_ID")
    FIRSTNAME = models.CharField(max_length=50)
    LASTNAME = models.CharField(max_length=50)
    GENDER = models.CharField(max_length=10, choices=GENDER_CHOICES)
    ADDRESS = models.CharField(max_length=255)
    CITY = models.CharField(max_length=50)
    STATE = models.CharField(max_length=50)
    PINCODE = models.IntegerField(max_length=15)
    PHONE = models.CharField(max_length=15)


    def __unicode__(self):
        return u'%s %s' % (self.FIRSTNAME, self.LASTNAME)


    class Meta:
        db_table = "CLIENT"



class Organisation(models.Model):
    ID = models.AutoField(primary_key=True)
    ORG_USERID_ID = models.ForeignKey(Users, verbose_name="User", db_column="ORG_USERID_ID")
    ORG_REGNO = models.CharField(max_length=30, unique=True, verbose_name="REG NO")
    ORG_NAME = models.CharField(max_length=50, verbose_name="NAME")
    ORG_ADDRESS = models.CharField(max_length=255, verbose_name="ADDRESS")
    ORG_CITY = models.CharField(max_length=50, verbose_name="CITY")
    ORG_STATE = models.CharField(max_length=50, verbose_name="STATE")
    ORG_PINCODE = models.IntegerField(max_length=15, verbose_name="PINCODE")
    ORG_PHONE = models.CharField(verbose_name='Telepone', max_length=15)
    ORG_ALTPHONE = models.CharField(verbose_name='Alternate Contact No', max_length=15)
    ORG_SECTOR = models.CharField(max_length=10, choices=SECTOR, verbose_name="SECTOR")

    def __unicode__(self):
        return u'%s' % (self.ORG_NAME)

    class Meta:
        db_table = "ORGANIZATION"



class Officer(models.Model):
    ID = models.AutoField(primary_key=True)
    OFF_USERID_ID = models.ForeignKey(Users, verbose_name="User", db_column="OFF_USERID_ID")
    OFF_ORG_ID = models.ForeignKey(Organisation, verbose_name="Organisation", db_column="OFF_ORG_ID")
    OFF_DESIGNATION = models.CharField(max_length=50, verbose_name="Designation")
    OFF_FIRSTNAME = models.CharField(max_length=50, verbose_name="First Name")
    OFF_LASTNAME = models.CharField(max_length=50, verbose_name="Last Name")
    OFF_GENDER = models.CharField(max_length=10, choices=GENDER_CHOICES, verbose_name="Gender")
    OFF_DOMAIN = models.CharField(max_length=50, verbose_name="Domain")
    OFF_DEPARTMENT = models.CharField(max_length=50, verbose_name="Department")
    OFF_ADDRESS = models.CharField(max_length=255, verbose_name="Officer Address")
    OFF_PINCODE = models.CharField(max_length=15, verbose_name="Pincode")
    OFF_PHONE = models.CharField(max_length=15, verbose_name="Phone")
    OFF_KEYWORDS = models.TextField(verbose_name='Keywords (seperate using space)')

    def __unicode__(self):
        return u'%s %s' % (self.OFF_FIRSTNAME, self.OFF_LASTNAME)

    class Meta:
        db_table = "OFFICER"



class GrievanceRegistration(models.Model):
    ID = models.AutoField(primary_key=True)
    GR_CLIENT_ID = models.ForeignKey(Client, verbose_name="Client", db_column="GR_CLIENT_ID")
    GR_OFFICER_ID = models.ForeignKey(Officer, blank=True, verbose_name="Officer", db_column="GR_OFFICER_ID")
    GR_ORGANIZATION_ID = models.ForeignKey(Organisation, verbose_name="Organisation", db_column="GR_ORGANIZATION_ID")
    GR_SECTOR = models.CharField(max_length=10, choices=SECTOR, verbose_name="Sector")
    GR_DESCRIPTION = models.TextField(verbose_name='A Short Description')
    GR_STATE = models.CharField(max_length=50, verbose_name="State")
    GR_CITY = models.CharField(max_length=50, verbose_name="City")
    GR_LOCATION = models.CharField(max_length=255, verbose_name='Landmark/Street/Locality')
    GR_PINCODE = models.IntegerField(max_length=15, verbose_name="Pincode")
    GR_DATE = models.DateTimeField(auto_now=True, verbose_name="Date")
    #GR_PICS = models.ImageField(blank=True, upload_to='Images_%d-%m-%Y')
    GR_STATUS = models.CharField(max_length=1, choices=REDRESSED_CHOICES, verbose_name="Status", default="N")


    def __unicode__(self):
        return u'%s %s %s' % (self.GR_CLIENT_ID.FIRSTNAME, self.GR_OFFICER_ID.OFF_FIRSTNAME, self.GR_DATE)

    class Meta:
        db_table = "GRIEVANCEREGISTRATION"



class GrievanceRedressed(models.Model):
    ID = models.AutoField(primary_key=True)
    RG_GRIEVANCE_ID = models.ForeignKey(GrievanceRegistration, verbose_name="Grievance", db_column="RG_GRIEVANCE_ID")
    RG_DATE = models.DateTimeField(auto_now=True, verbose_name="Date")
    RG_TIMETAKEN = models.CharField(max_length=10, verbose_name="Time Taken")
    RG_MESSAGE = models.CharField(max_length=255, verbose_name="Message")

    def __unicode__(self):
        return u'%s %s' % (self.RG_GRIEVANCE_ID, self.RG_DATE)

    class Meta:
        db_table = "GRIEVANCEREDRESSED"




