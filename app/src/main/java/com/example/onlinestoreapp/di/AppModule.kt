package com.example.onlinestoreapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.onlinestoreapp.data.db.userdb.UserDao
import com.example.onlinestoreapp.data.db.userdb.UserDatabase
import com.example.onlinestoreapp.data.repository.AuthenticationRepositoryImpl
import com.example.onlinestoreapp.data.repository.ProductRepositoryImpl
import com.example.onlinestoreapp.data.repository.UserRepositoryImpl
import com.example.onlinestoreapp.data.repository.UserRoomRepositoryImpl
import com.example.onlinestoreapp.domain.repository.AuthenticationRepository
import com.example.onlinestoreapp.domain.repository.ProductRepository
import com.example.onlinestoreapp.domain.repository.UserRepository
import com.example.onlinestoreapp.utils.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "user_profile_entity").build()

    @Provides
    fun provideUserDao(database: UserDatabase) = database.userDao()

    @Provides
    fun provideRepo(userDao:UserDao) : UserRoomRepositoryImpl = UserRoomRepositoryImpl(userDao)
}

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.USER_ID, Context.MODE_PRIVATE)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModules {

    @Binds
    fun provideAuthRepository(repository: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    fun provideUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    fun provideProductRepository(repository: ProductRepositoryImpl) : ProductRepository

}