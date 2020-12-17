import { shallow, configure } from 'enzyme';
import AddBook from '../components/add-book';
import { render, screen } from '@testing-library/react';
import Adapter from 'enzyme-adapter-react-16';
configure({adapter: new Adapter()});

test('add-book', () => {
    const component = shallow(<AddBook/>);
    const component1 = component.find('.add')
    expect(component1.length).toBe(1);
})


  
 