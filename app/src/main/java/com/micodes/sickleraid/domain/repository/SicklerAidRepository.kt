package com.micodes.sickleraid.domain.repository

import androidx.lifecycle.LiveData
import com.micodes.sickleraid.domain.model.Doctor
import com.micodes.sickleraid.domain.model.MedicalRecords

interface SicklerAidRepository {
//Functions
    suspend fun getAllDoctors(): LiveData<List<Doctor>>

    suspend fun upsertDoctor(doctor: Doctor)

    suspend fun insertMedicalRecords(records: MedicalRecords)

}