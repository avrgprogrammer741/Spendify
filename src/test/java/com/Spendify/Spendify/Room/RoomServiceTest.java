//package com.Spendify.Spendify.Room;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
////import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
//import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
//import static org.mockito.Mockito.verify;
//
//class RoomServiceTest {
//    @Mock private RoomRepository roomRepository;
//    private RoomDTOMapper roomDTOMapper;
//    private RoomService underTest;
//    private AutoCloseable autoCloseable;
//
//
//    @BeforeEach
//    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
//        roomDTOMapper = new RoomDTOMapper();
//        underTest = new RoomService(roomRepository, roomDTOMapper);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }
//
//    @Test
//    void canGetAllRooms() {
////        when
//        underTest.getAllRooms();
////        then
//        verify(roomRepository).findAll();
//
//    }
//
////    @Test
////    @Disabled
////    void getRoom() {
//////        when
////        underTest.getRoom()
//////        then
////
////    }
//
//    @Test
//    @Disabled
//    void deleteRoom() {
//    }
//
//    @Disabled
//    @Test
//    void canCreateRoom() {
////        given
//        underTest.createRoom();
//        ArgumentCaptor<Room> roomArgumentCaptor =
//                ArgumentCaptor.forClass(Room.cla    ss);
//        verify(roomRepository)
//                .save(roomArgumentCaptor.capture());
////           when
//        Room capturedRoom = roomArgumentCaptor.getValue();
//    }
//
//    @Disabled
//    @Test
//    void removeUser() {
//    }
//}