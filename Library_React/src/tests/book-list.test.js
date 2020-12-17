import { shallow, configure } from 'enzyme';
import BookList from '../components/book-list';
import { render, screen } from '@testing-library/react';
import Adapter from 'enzyme-adapter-react-16';
configure({adapter: new Adapter()});

test('book-list', () => {
    const component = shallow(<BookList/>);
    const component1 = component.find('.booklist')
    expect(component1.length).toBe(1);
})


  
 