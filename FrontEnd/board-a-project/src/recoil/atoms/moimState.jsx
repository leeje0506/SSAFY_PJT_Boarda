import { atom } from 'recoil';

export const moimListState = atom({
  key: 'moimListState', // 고유한 key
  default: [], // 기본값
});

export const locationState = atom({
  key: 'locationState',
  default: '서울시 강남구', // 초기값
});

export const moimState = atom({
  key: 'moimState',
  default: {
    userId: 10,
    title: '',
    content: '',
    number: 2,
    location: "",
    datetime: new Date(),
    // friends: [],
  },
});